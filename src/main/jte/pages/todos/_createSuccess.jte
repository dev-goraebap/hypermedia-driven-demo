@param com.example.demo.app.domain.Todo todo = null

<!-- 에러박스가 표시되었을 수도 있기 때문에 에러박스 초기화 -->
<div id="HTMX_TODO_FORM_ERROR" hx-swap-oob="true"></div>

<!-- 투두 아이템을 Form의 htmx 요청에 따라 #HTMX_TODOLIST의 마지막 자식 요소로 추가 -->
@template.pages.todos._item(todo = todo)
