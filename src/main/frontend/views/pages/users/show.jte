@param com.example.demo.app.domain.User user

@template.layouts.default(title = "내 정보", content = @`
    <div class="flex flex-col gap-2">
        <p>${user.getNickname()}</p>
        <p class="text-sm">${user.getEmail()}</p>
    </div>
`)
