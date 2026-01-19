@param com.example.demo.app.domain.Todo todo = null

@if(todo != null)
    <li class="p-3 border-b flex justify-between">
        <p>${todo.getContent() }</p>
        <div class="flex gap-2">
            <button hx-get="/todos/${todo.getId()}/edit"
                    hx-target="#HTMX_MODAL"
                    hx-swap="innerHTML"
                    hx-sync="this:abort"
                    class="editModal text-xs text-yellow-500">
                수정
            </button>
            <button hx-delete="/todos/${todo.getId()}"
                    class="text-xs text-red-500">
                삭제
            </button>
        </div>
    </li>
@endif
