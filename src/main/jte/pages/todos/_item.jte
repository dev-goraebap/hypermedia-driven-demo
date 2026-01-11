@param com.example.demo.app.domain.Todo todo = null

@if(todo != null)
    <li class="p-3 border-b flex justify-between">
        <p>${todo.getContent() }</p>
        <button hx-delete="/todos/${todo.getId()}"
                hx-trigger="click"
                hx-target="closest li"
                hx-swap="outerHTML"
                hx-target-4xx="#HTMX_TODO_DELETE_ERROR"
                class="text-xs text-red-500">
            삭제
        </button>
    </li>
@endif
