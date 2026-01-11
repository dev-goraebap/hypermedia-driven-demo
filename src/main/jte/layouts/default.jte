@param gg.jte.Content content
@param String title = "App"

<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Demo | ${title}</title>
    <!-- tailwindcss 추가 -->
    <script src="https://cdn.jsdelivr.net/npm/@tailwindcss/browser@4"></script>
</head>
<body>
<div class="mx-auto w-full max-w-xl h-screen p-4">
    <nav class="border-y p-3">
        <a href="/todos">목록</a>
        <span>/</span>
        <a href="/todos/register">등록</a>
    </nav>

    <br/>
    <h1 class="text-3xl font-extrabold">Spring Boot + Htmx Example</h1>
    <p class="text-sm">Hypermedia driven applications demo</p>
    <br/>

    ${content}
</div>
</body>
</html>