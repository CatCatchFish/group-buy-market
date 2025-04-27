document.addEventListener("DOMContentLoaded", () => {
  const loginForm = document.getElementById("loginForm")
  const errorMessage = document.getElementById("errorMessage")

  loginForm.addEventListener("submit", (e) => {
    e.preventDefault()

    let baseUrl = "http://localhost:8092";
    const code = document.getElementById("code").value

    fetch(baseUrl + '/api/v1/auth/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
      body: new URLSearchParams({
        code: code  // 替换为实际的验证码
      })
    }).then(response => response.json())
      .then(data => {
        if (data.code === '0000') {
          // 登录成功
          errorMessage.style.display = "none"

          // 设置Cookie (有效期为1天)
          const expirationDate = new Date()
          expirationDate.setDate(expirationDate.getDate() + 1)
          document.cookie = `username=${data.data}; expires=${expirationDate.toUTCString()}; path=/`

          // 跳转到首页
          window.location.href = "index.html"
        } else {
          console.error('Error:', data.info)
          alert('登录失败，请检查验证码')
        }
      })
      .catch(error => {
        console.error('Error:', error)
        alert('登录失败，请检查验证码')
      });
  })
})

