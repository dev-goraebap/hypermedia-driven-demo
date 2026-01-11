@param java.util.List<String> errors = null

<div id="HTMX_TODO_FORM_ERROR" hx-swap-oob="true">
    @if(errors!=null && !errors.isEmpty())
        <div class="border-l-4 border-red-500 bg-red-300 text-red-700 p-3 rounded-xl mt-4">
            @for(var error: errors)
                <p>${error}</p>
            @endfor
        </div>
    @endif
</div>