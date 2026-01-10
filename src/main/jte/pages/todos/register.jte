@import com.example.demo.app.dto.TodoCreateRequest
@import java.util.List

@param TodoCreateRequest todoCreateRequest = null
@param List<String> errors = null

!{ var content = todoCreateRequest != null ? todoCreateRequest.content() : ""; }

@template.layouts.default(title = "todo list", content = @`
    <form action="/todos" method="post" class="w-full flex gap-2">
        <label for="contentInput" class="flex-8">
            <input id="contentInput"
                   type="text"
                   name="content"
                   placeholder="할일 입력.."
                   value="${content}"
                   class="w-full border p-3 rounded-xl">
        </label>
        <button class="p-3 bg-blue-500 text-white flex-4 rounded-xl">추가</button>
    </form>

    @if(errors!=null && !errors.isEmpty())
        <div class="border-l-4 border-red-500 bg-red-300 text-red-700 p-3 rounded-xl mt-4">
            @for(var error: errors)
                <p>${error}</p>
            @endfor
        </div>
    @endif
`)
