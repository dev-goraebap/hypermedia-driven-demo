import htmx from 'htmx.org';
import 'htmx-ext-response-targets';

window.htmx = htmx;

class Modal {
    static #currentTrigger = null;

    static open(triggerElt) {
        this.#currentTrigger = triggerElt;

        const container = document.querySelector('#modalContainer');
        container.classList.replace('hidden', 'block');
        container.animate([
            {opacity: 0},
            {opacity: 1}
        ], {duration: 200, easing: 'ease-out'});
    }

    static close() {
        if (this.#currentTrigger) {
            htmx.trigger(this.#currentTrigger, 'htmx:abort');
            this.#currentTrigger = null;
        }

        const container = document.querySelector('#modalContainer');
        const content = document.querySelector('#HTMX_MODAL');
        return new Promise((resolve) => {
            container.animate([
                {opacity: 1},
                {opacity: 0}
            ], {
                duration: 200,
                easing: 'ease-out'
            }).onfinish = () => {
                container.classList.replace('block', 'hidden');
                content.innerHTML = '로딩중...';
                resolve();
            };
        });
    }
}

window.Modal = Modal;

window.addEventListener('htmx:beforeRequest', (event) => {
    if (event.detail.target.id === 'HTMX_MODAL') {
        Modal.open(event.detail.elt);
    }
});

window.addEventListener('htmx:beforeOnLoad', async (event) => {
    const xhr = event.detail.xhr;
    const hxLocation = xhr.getResponseHeader('HX-Location');

    const isModalOpened = document.querySelector('#modalContainer')?.classList.contains('block');
    if (hxLocation && isModalOpened) {
        event.preventDefault();

        await Modal.close();
        htmx.ajax('GET', hxLocation, {target: 'body', swap: 'innerHTML'});
    }
});
