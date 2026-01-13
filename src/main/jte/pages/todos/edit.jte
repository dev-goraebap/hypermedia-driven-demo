<%-- todo 데이터가 무조건 있어야함 --%>
@param com.example.demo.app.domain.Todo todo

<div>
    <%-- 모달 오버레이 --%>
    <div class="fixed inset-0 bg-black/70" onclick="this.parentNode.remove()"></div>

    <%-- 모달 박스 --%>
    <div class="fixed left-[50%] translate-x-[-50%] top-[50%] translate-y-[-50%] w-full max-w-md rounded-xl p-4 bg-white">

        <form hx-put="/todos/${todo.getId()}"
              hx-target-4xx="#HTMX_TODO_EDIT_ERROR"
              hx-disabled-elt="find button"
              class="flex flex-col gap-4">
            <label for="updateContentInput" class="w-full">
                <input id="updateContentInput"
                       type="text"
                       name="content"
                       value="${todo.getContent()}"
                       placeholder="할일 입력"
                       class="p-3 border rounded-xl w-full">
            </label>

            <button class="bg-blue-500 text-white p-3 rounded-xl">
                <span class="default">변경</span>
                <span class="loading">로딩중..</span>
            </button>
        </form>

        <div id="HTMX_TODO_EDIT_ERROR"></div>
    </div>
</div>