//刷新验证码
$('#verCodeImg').click(function flushVerCode() {
    this.src = this.src + "?" + Math.random();
});