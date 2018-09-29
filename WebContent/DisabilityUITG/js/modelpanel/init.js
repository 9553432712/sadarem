/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
// Preload Images
img1 = new Image(16, 16);
img1.src="./DisabilityUITG/images/spinner.gif";

img2 = new Image(220, 19);
img2.src="./DisabilityUITG/images/ajax-loader.gif";

// When DOM is ready
$(document).ready(function(){

    // Launch MODAL BOX if the Login Link is clicked
    $("#login_link").click(function(){
        $('#login_form').modal();
        document.forms[0].userid.focus();
    });

    // When the form is submitted
    $("#status > form").submit(function(){

        // Hide 'Submit' Button
        $('#submit').hide();

        // Show Gif Spinning Rotator
        $('#ajax_loading').show();


        // 'this' refers to the current submitted form
        var str = $(this).serialize();

        // -- Start AJAX Call --


        if(document.forms[0].userid.value=="") {
            alert("Please Enter UserName");
            document.forms[0].userid.value="";
            document.forms[0].userid.focus();
            $('#submit').show();
            document.getElementById("ajax_loading").style.display="none";
            return false;
        }else if(document.forms[0].password.value=="") {
            alert("Please Enter Password");
            document.forms[0].password.value="";
            document.forms[0].password.focus();
            $('#submit').show();
            document.getElementById("ajax_loading").style.display="none";
            return false;
        }else {
            with(document.forms[0]) {
                encrptPwd.value = calcMD5(elements["password"].value);
                }
        }

        $.ajax({
            type: "POST",
            url: "login.do?mode=checkLogin",  // Send the login info to this page
            data: str,
            success: function(msg){

                $("#status").ajaxComplete(function(event, request, settings){

                    // Show 'Submit' Button
                    $('#submit').show();

                    // Hide Gif Spinning Rotator
                    $('#ajax_loading').hide();

                    var myMsg=$.trim(msg);
                    if(myMsg == "OK") // LOGIN OK?
                    {

                        var login_response = '<div id="logged_in">' +
                        '<div style="width: 350px; float: left; margin-left: 70px;">' +
                        '<div style="width: 40px; float: left;">' +
                        '<img style="margin: 10px 0px 10px 0px;" align="absmiddle" src="./DisabilityUITG/images/ajax-loader.gif">' +
                        '</div>' +
                        '<div style="margin: 10px 0px 0px 10px; float: right; text-align: left; width: 300px;">'+
                        "You are successfully logged in! <br /> Please wait while you're redirected...</div></div>";

                        $('a.modalCloseImg').hide();

                        $('#simplemodal-container').css("width","500px");
                        $('#simplemodal-container').css("height","120px");

                        $(this).html(login_response); // Refers to 'status'

                        go_to_private_page(); // Go to Welcome Page

                    }else  if(myMsg == "rdCallCenterDPMRequests") // LOGIN OK?
                    {

                        var login_response = '<div id="logged_in">' +
                        '<div style="width: 350px; float: left; margin-left: 70px;">' +
                        '<div style="width: 40px; float: left;">' +
                        '<img style="margin: 10px 0px 10px 0px;" align="absmiddle" src="./DisabilityUITG/images/ajax-loader.gif">' +
                        '</div>' +
                        '<div style="margin: 10px 0px 0px 10px; float: right; text-align: left; width: 300px;">'+
                        "You are successfully logged in! <br /> Please wait while you're redirected...</div></div>";

                        $('a.modalCloseImg').hide();

                        $('#simplemodal-container').css("width","500px");
                        $('#simplemodal-container').css("height","120px");

                        $(this).html(login_response); // Refers to 'status'

                        go_to_dpmLogin_page(); // Go to Welcome Page

                    }else  if(myMsg == "rdCallCenterPDRequests") // LOGIN OK?
                    {

                        var login_response = '<div id="logged_in">' +
                        '<div style="width: 350px; float: left; margin-left: 70px;">' +
                        '<div style="width: 40px; float: left;">' +
                        '<img style="margin: 10px 0px 10px 0px;" align="absmiddle" src="./DisabilityUITG/images/ajax-loader.gif">' +
                        '</div>' +
                        '<div style="margin: 10px 0px 0px 10px; float: right; text-align: left; width: 300px;">'+
                        "You are successfully logged in! <br /> Please wait while you're redirected...</div></div>";

                        $('a.modalCloseImg').hide();

                        $('#simplemodal-container').css("width","500px");
                        $('#simplemodal-container').css("height","120px");

                        $(this).html(login_response); // Refers to 'status'

                        go_to_pdLogin_page(); // Go to Welcome Page

                    }else  if(myMsg == "PasswordChangePage") // LOGIN OK?
                    {

                        var login_response = '<div id="logged_in">' +
                        '<div style="width: 350px; float: left; margin-left: 70px;">' +
                        '<div style="width: 40px; float: left;">' +
                        '<img style="margin: 10px 0px 10px 0px;" align="absmiddle" src="./DisabilityUITG/images/ajax-loader.gif">' +
                        '</div>' +
                        '<div style="margin: 10px 0px 0px 10px; float: right; text-align: left; width: 300px;">'+
                        "You are successfully logged in! <br /> Please wait while you're redirected...</div></div>";

                        $('a.modalCloseImg').hide();

                        $('#simplemodal-container').css("width","500px");
                        $('#simplemodal-container').css("height","120px");

                        $(this).html(login_response); // Refers to 'status'

                        go_to_chgPwd_page(); // Go to Welcome Page

                    }else if(myMsg == "Failed1"){
                         document.forms[0].userid.value="";
                        document.forms[0].password.value="";
                          login_response = "<font color=\"red\" size=\"2\">UserName not exist</font";
                        $('#login_response').html(login_response);
                    }else if(myMsg == "Failed2"){
                        document.forms[0].userid.value="";
                        document.forms[0].password.value="";
                          login_response = "<font color=\"red\" size=\"2\">Another person logged by using this Username</font";
                        $('#login_response').html(login_response);
                    }else if(myMsg == "Failed3"){
                        document.forms[0].userid.value="";
                        document.forms[0].password.value="";
                          login_response = "<font color=\"red\" size=\"2\">User account has been locked. Contact Camp Incharge to Unlock</font";
                        $('#login_response').html(login_response);

                    }else if(myMsg == "Failed5"){
                        document.forms[0].userid.value="";
                        document.forms[0].password.value="";
                          login_response = "<font color=\"red\" size=\"2\">Invalid UserName/Password</font";
                        $('#login_response').html(login_response);


                    }
                    else // ERROR?
                    {            document.forms[0].userid.value="";
                        document.forms[0].password.value="";
                        login_response = "<font color=\"red\" size=\"2\">Incorrect Login Details</font";
                        $('#login_response').html(login_response);
                    }

                });

            }

        });

        // -- End AJAX Call --

        return false;

    }); // end submit event

});

function go_to_private_page()
{
    window.location = 'loginHome.do'; // Members Area
}

function go_to_dpmLogin_page()
{
    window.location = 'dpmHomePage.do'; // Members Area
}
function go_to_pdLogin_page()
{
    alert("RD PD");
    window.location = 'pdHomePage.do'; // Members Area
}
function go_to_chgPwd_page()
{
    window.location = 'changePwdPage.do'; // Members Area
}


