@param com.example.demo.app.domain.Todo todo = null

<li class="p-3 border-b flex justify-between">
    <p>${todo != null ? todo.getContent() : "" }</p>
</li>