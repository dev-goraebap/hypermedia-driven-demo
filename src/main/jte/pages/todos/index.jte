@import com.example.demo.app.domain.Todo
@import java.util.List

@param List<Todo> todoList = new java.util.ArrayList()

@template.layouts.default(title = "todo list", content = @`
    <ul class="border-t mt-4">
        @for(var todo: todoList)
            <li class="p-3 border-b flex justify-between">
                <p>${todo.getContent()}</p>
            </li>
        @endfor
    </ul>
`)
