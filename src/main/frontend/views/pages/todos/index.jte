@param java.util.List<String> errors = null
@param java.util.List<com.example.demo.app.domain.Todo> todoList = new java.util.ArrayList()

@template.layouts.default(title = "할일 목록", content = @`
    <form hx-post="/todos"
          <%-- body의 hx-target-4xx 를 사용하지 않고 오버라이드함 --%>
          hx-target-4xx="#HTMX_TODO_FORM_ERROR"
          hx-disabled-elt="find button"
          class="w-full flex flex-col gap-2">
        <div class="flex gap-2">
            <label for="contentInput" class="flex-8">
                <input id="contentInput"
                       hx-get="/todos/check-duplicate"
                       hx-trigger="keyup[target.value.length >= 2] changed delay:500ms"
                       hx-target="#HTMX_DUPLICATE_CHECK_BOX"
                       hx-target-4xx="#HTMX_DUPLICATE_CHECK_BOX"
                       hx-swap="outerHTML"
                       hx-disabled-elt="#HTMX_TODO_ADD_BTN"
                       type="text"
                       name="content"
                       placeholder="할일 입력.."
                       class="w-full border p-3 rounded-xl">
            </label>
            <button id="HTMX_TODO_ADD_BTN" class="p-3 bg-blue-500 text-white flex-4 rounded-xl disabled:opacity-75">
                <span class="default">추가</span>
                <span class="loading">로딩중..</span>
            </button>
        </div>
        <div id="HTMX_DUPLICATE_CHECK_BOX"></div>
    </form>
    <!-- 투두 등록 폼 에러에만 사용 -->
    <div id="HTMX_TODO_FORM_ERROR"></div>

    <ul id="HTMX_TODOLIST" class="border-t mt-4">
        @for(var todo: todoList)
            @template.pages.todos._item(todo = todo)
        @endfor
    </ul>
`)