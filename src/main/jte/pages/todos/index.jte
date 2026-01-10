@import com.example.demo.app.domain.Todo
@import java.util.List

@param List<Todo> todoList = new java.util.ArrayList()
@param List<String> errors = null

<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Demo | App</title>
    <!-- tailwindcss 추가 -->
    <script src="https://cdn.jsdelivr.net/npm/@tailwindcss/browser@4"></script>

    <!-- htmx 추가 -->
    <script src="https://cdn.jsdelivr.net/npm/htmx.org@2.0.8/dist/htmx.min.js"
            integrity="sha384-/TgkGk7p307TH7EXJDuUlgG3Ce1UVolAOFopFekQkkXihi5u/6OCvVKyz1W+idaz"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="mx-auto w-full max-w-xl h-screen p-4">
    <h1 class="text-3xl font-extrabold">Spring Boot + Htmx Example</h1>
    <p class="text-sm">Hypermedia driven applications demo</p>
    <br/>
    <form action="/todos" method="post" class="w-full flex gap-2">
        <label for="contentInput" class="flex-8">
            <input id="contentInput"
                   type="text"
                   name="content"
                   placeholder="할일 입력.."
                   class="w-full border p-3 rounded-xl">
        </label>

        <button class="p-3 bg-blue-500 text-white flex-4 rounded-xl">추가</button>
    </form>

    <ul class="border-t mt-4">
        @for(var todo: todoList)
            <li class="p-3 border-b flex justify-between">
                <p>${todo.getContent()}</p>
                <form action="/todos/${todo.getId()}" method="post">
                    <button>삭제</button>
                </form>
            </li>
        @endfor
    </ul>
</div>
</body>
</html>