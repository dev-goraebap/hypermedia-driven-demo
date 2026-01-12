@param java.util.List<String> errors = null
@param boolean isDuplicated = false

<div id="HTMX_DUPLICATE_CHECK_BOX">
    <%-- 유효성 검증에서 에러가 나면 여기 표시 --%>
    @if(errors != null && !errors.isEmpty())
        @for(var error: errors)
            <span class="text-xs text-red-500">${error}</span>

            <%-- 클릭할 수 없는 버튼으로 OOB 교체 --%>
            <button id="HTMX_TODO_ADD_BTN"
                    hx-swap-oob="true"
                    disabled="disabled"
                    class="p-3 bg-blue-500 text-white flex-4 rounded-xl disabled:opacity-75">
                추가
            </button>
        @endfor
    <%-- 유효성을 통과했다면 정상적으로 중복여부 표시 --%>
    @else
        @if(isDuplicated)
            <span class="text-xs text-red-500">중복되는 컨텐츠입니다.</span>

            <%-- 클릭할 수 없는 버튼으로 OOB 교체 --%>
            <button id="HTMX_TODO_ADD_BTN"
                    hx-swap-oob="true"
                    disabled="disabled"
                    class="p-3 bg-blue-500 text-white flex-4 rounded-xl disabled:opacity-75">
                추가
            </button>
        @else
            <span class="text-xs text-blue-500">등록 가능한 컨텐츠 입니다.</span>

            <%-- 정상적인 버튼 반환 --%>
            <button id="HTMX_TODO_ADD_BTN"
                    hx-swap-oob="true"
                    class="p-3 bg-blue-500 text-white flex-4 rounded-xl disabled:opacity-75">
                <span class="default">추가</span>
                <span class="loading">로딩중..</span>
            </button>
        @endif
    @endif
</div>