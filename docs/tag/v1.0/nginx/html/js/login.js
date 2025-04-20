document.addEventListener("DOMContentLoaded", () => {
  const loginForm = document.getElementById("loginForm")
  const errorMessage = document.getElementById("errorMessage")

  loginForm.addEventListener("submit", (e) => {
    e.preventDefault()

    let baseUrl = "http://localhost:8091";
    const username = document.getElementById("username").value
    const password = document.getElementById("password").value

    fetch(baseUrl + '/api/v1/gbm/tag/add_to_tag', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        userId: username,
        tagId: 'CROWD_01'
      })
    }).then(response => response.json())

    // 登录成功
    errorMessage.style.display = "none"

    // 设置Cookie (有效期为1天)
    const expirationDate = new Date()
    expirationDate.setDate(expirationDate.getDate() + 1)
    document.cookie = `username=${username}; expires=${expirationDate.toUTCString()}; path=/`

    // 跳转到首页
    window.location.href = "index.html"
  })
})

