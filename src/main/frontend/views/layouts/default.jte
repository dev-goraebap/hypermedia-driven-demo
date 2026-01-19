@import gg.jte.Content
@import java.util.List

@param String title = "App"
@param Content content
@param List<String> errors = null

<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Demo | ${title}</title>
<!-- Vite 빌드 -->
<link rel="stylesheet" href="/builds/style.css">
<script type="module" src="/builds/app.main.js"></script>
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
<body hx-boost="true"
      hx-ext="response-targets"
      hx-target-4xx="#HTMX_DEFAULT_ERROR_MODAL">
<div class="mx-auto w-full max-w-xl h-screen p-4">
    <br/>
    <h1 class="text-3xl font-extrabold">Spring Boot + Htmx Example</h1>
    <p class="text-sm">Hypermedia driven applications demo</p>
    <br/>

    <nav>
        <ul class="flex gap-2">
            <li>
                <a href="/todos">할일</a>
            </li>
            <li>
                <a href="/users/me">내 정보</a>
            </li>
        </ul>
    </nav>
    <br/>

    <div>
        ${content}
    </div>

    <!-- 일반적인 4xx 에러들은 이 에러 모달을 사용하게 됨 -->
    <div id="HTMX_DEFAULT_ERROR_MODAL"></div>

    @template.partials.modal()
</div>
</body>
</html>