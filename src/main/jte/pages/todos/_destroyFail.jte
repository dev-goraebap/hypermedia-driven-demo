@param java.util.List<String> errors = null

<div id="HTMX_TODO_DELETE_ERROR" hx-swap-oob="true">
    <div>
        @if(errors!=null && !errors.isEmpty())
            <div class="fixed inset-0 bg-black/70" onclick="this.parentNode.remove()"></div>
            <div class="fixed left-[50%] translate-x-[-50%] top-[50%] translate-y-[-50%]
                border-l-4 border-red-500 text-red-700 rounded-xl p-3 bg-white">
                @for(var error: errors)
                    <p>${error}</p>
                @endfor
            </div>
        @endif
    </div>
</div>