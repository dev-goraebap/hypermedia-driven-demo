@import java.util.List

@param List<String> errors = null
@param java.util.List<com.example.demo.app.domain.Todo> todoList = new java.util.ArrayList()

<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Demo | Todo</title>
    <!-- tailwindcss 추가 -->
    <script src="https://cdn.jsdelivr.net/npm/@tailwindcss/browser@4"></script>

    <!-- htmx 추가 -->
    <script src="https://cdn.jsdelivr.net/npm/htmx.org@2.0.8/dist/htmx.min.js"
            integrity="sha384-/TgkGk7p307TH7EXJDuUlgG3Ce1UVolAOFopFekQkkXihi5u/6OCvVKyz1W+idaz"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/htmx-ext-response-targets@2.0.4"
            integrity="sha384-T41oglUPvXLGBVyRdZsVRxNWnOOqCynaPubjUVjxhsjFTKrFJGEMm3/0KGmNQ+Pg"
            crossorigin="anonymous"></script>
</head>
<style>
    button span.loading {
        display: none;
    }

    .htmx-request button span.default {
        display: none;
    }

    .htmx-request button span.loading {
        display: inline;
    }
</style>
<body hx-ext="response-targets" hx-target-4xx="#HTMX_DEFAULT_ERROR_MODAL">
<div class="mx-auto w-full max-w-xl h-screen p-4">
    <br/>
    <h1 class="text-3xl font-extrabold">Spring Boot + Htmx Example</h1>
    <p class="text-sm">Hypermedia driven applications demo</p>
    <br/>

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

    <!-- 일반적인 4xx 에러들은 이 에러 모달을 사용하게 됨 -->
    <div id="HTMX_DEFAULT_ERROR_MODAL"></div>

    <div id="HTMX_EDIT_MODAL"></div>
</div>
</body>
</html>