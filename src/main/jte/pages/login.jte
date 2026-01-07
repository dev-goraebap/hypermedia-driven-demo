@param java.util.List<String> errors

<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Demo | 로그인</title>
    <!-- tailwindcss 추가 -->
    <script src="https://cdn.jsdelivr.net/npm/@tailwindcss/browser@4"></script>
</head>
<body class="h-screen flex justify-center items-center">
<form action="/login" method="post"
      class="w-full max-w-xl [&_input]:w-full [&_input]:border [&_input]:p-3">

    <%-- 에러 목록 값이 있을 경우 노출됨 --%>
    @if(errors != null && !errors.isEmpty())
        <div class="border-l-4 border-red-500 bg-red-200 text-red-700 rounded-xl p-4">
            @for(var error: errors)
                <p>${error}</p>
            @endfor
        </div>
        <br/>
    @endif

    <label for="usernameInput">
        <input id="usernameInput" type="text" name="username" placeholder="username">
    </label>
    <label for="passwordInput">
        <input id="passwordInput" type="password" name="password" placeholder="password">
    </label>
    <button class="w-full bg-blue-500 text-white p-3">로그인</button>
</form>
</body>
</html>