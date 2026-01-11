@param com.example.demo.app.domain.Todo todo = null

@if(todo != null)
    <li class="p-3 border-b flex justify-between">
        <p>${todo.getContent() }</p>
        <button hx-delete="/todos/${todo.getId()}"
                class="text-xs text-red-500">
            삭제
        </button>
    </li>
@endif
