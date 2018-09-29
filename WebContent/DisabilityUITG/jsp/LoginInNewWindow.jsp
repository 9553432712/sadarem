
<script>
    function maximize() {
    window.moveTo(0, 0);
    window.resizeTo(screen.width, screen.height);
    window.opener = self
    window.close();
    
    }
    maximize();
    function openNewWindow(){
    
    window.open('loginInNewWindow.do','','toolbar=0,scrollbars=1,location=0,directories=0,status=1,menubars=0'); 
   
}
    window.onload=openNewWindow();
</script>

