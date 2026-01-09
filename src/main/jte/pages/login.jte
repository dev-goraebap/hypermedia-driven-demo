@param java.util.List<String> errors
@param com.example.demo.app.dto.LoginRequest loginRequest = null

@template.layouts.default(title="로그인", content = @`
    <div class="h-screen flex justify-center items-center">
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
                <input id="usernameInput" type="text" name="username" placeholder="username"
                       value="${loginRequest != null ? loginRequest.username() : ""}">
            </label>
            <label for="passwordInput">
                <input id="passwordInput" type="password" name="password" placeholder="password">
            </label>
            <button class="w-full bg-blue-500 text-white p-3">로그인</button>
        </form>
    </div>
`)