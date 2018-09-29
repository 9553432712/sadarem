<%--
    Document   : changeParticularDetails
    Created on : Dec 21, 2011, 12:47:40 PM
    Author     : 484898
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page import="org.bf.disability.dto.RequestInformationDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.bf.disability.dto.RequestInformationDTO"%>
<%@page import="org.bf.disability.dto.NameRelationChangeDTO"%>
<% String personname = "&#3093;&#3134;&#3120;&#3149;&#3105;&#3137; &#3118;&#3136;&#3110; &#3098;&#3138;&#3114;&#3125;&#3122;&#3128;&#3135;&#3112;  &#3114;&#3143;&#3120;&#3137; ";
            String fathername = "&#3108;&#3074;&#3105;&#3149;&#3120;&#3135; / &#3128;&#3074;&#3120;&#3093;&#3149;&#3127;&#3093;&#3137;&#3105;&#3135; &#3114;&#3143;&#3120;&#3137;";
            String telugu = "&#3108;&#3142;&#3122;&#3137;&#3095;&#3137;&#3122;&#3147;";
            //String hospitalname= (String)request.getAttribute("HospitalName");


            NameRelationChangeDTO nameRelationChangeDTO = new NameRelationChangeDTO();

            int i = 1;
            int oldParticularId = 0;

            String list = null;
            String requestIdPersonalData = (String) request.getAttribute("requestIdPersonalData");
            ArrayList oldParticularList = new ArrayList();
            if (request.getAttribute("oldParticularList") != null) {
                oldParticularList = (ArrayList) request.getAttribute("oldParticularList");
            }

            request.setAttribute("personCodeId", request.getAttribute("personCodeId"));
            request.setAttribute("requesttypeId", request.getAttribute("requesttypeId"));
            request.setAttribute("requestIdData", request.getAttribute("requestIdData"));

            oldParticularList = (ArrayList) request.getAttribute("oldParticularList");

            // String Type1 = (String) request.getAttribute("Type1");
            // String Type2 = (String) request.getAttribute("Type2");
            // String Type5 = (String) request.getAttribute("Type5");

            // request.setAttribute("TypeData1", Type1);
            // request.setAttribute("TypeData2", Type2);

            if (request.getAttribute("oldParticularId") != null) {

                oldParticularId = Integer.parseInt((String) request.getAttribute("oldParticularId").toString());
            }
            String requestId = (String) request.getAttribute("requestId");
            String personCode = (String) request.getAttribute("personCode");
            String dOBDetails = (String) request.getAttribute("dOBDetails");

            String requestFormId = (String) request.getAttribute("requestFormId");
            String personCodeFormId = (String) request.getAttribute("personCodeFormId");

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>

    </head>

    <link rel="stylesheet" href="./DisabilityUITG/css/cssmaster.css" type="text/css">
    <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
    <script>

        function description()
        {

            document.txtDescription.value=document.telugu.value;
        }
        function Catname()
        {

            document.txtCatName.value=document.telugu.value;
        }
        function Flashnews()
        {

            document.txtFlashNews.value=document.telugu.value;
        }
        function Videos()
        {

            document.txthvsubject.value=document.telugu.value;
        }
        function  description()
        {
            document.txthvdescription.value=document.telugu.value;
        }
        var vowels = "(A(O)?)|(E)|(H)|(I)|(M)|(O)|(TR)|(U)|(a((a)|(e)|(i)|(u))?)|(e(e)?)|(i)|(o((a)|(o))?)|(tR)|(u)"
        var consonants = "(B(h)?)|(Ch)|(D(h)?)|(G)|(J(h)?)|(K(h)?)|(L)|(N)|(R)|(Sh)|(T(h)?)|(b(h)?)|(ch)|(d(h)?)|(g(h)?)|(h)|(j(h)?)|(k(h)?)|(l)|(m)|(n(Y)?)|(p(h)?)|(r)|(s(h)?)|(t(h)?)|(v)|(y)"
        var letter_codes = {
            "~a" : "&#3077;",
            "~aa" : "&#3078;",
            "~A" : "&#3078;",
            "~i" : "&#3079;",
            "~ee" : "&#3080;",
            "~I" : "&#3080;",
            "~u" : "&#3081;",
            "~oo" : "&#3082;",
            "~U" : "&#3082;",
            "~e" : "&#3086;",
            "~ae" : "&#3087;",
            "~E" : "&#3087;",
            "~ai" : "&#3088;",
            "~o" : "&#3090;",
            "~oa" : "&#3091;",
            "~O" : "&#3091;",
            "~au" : "&#3092;",
            "~tR" : "&#3083;",
            "~TR" : "&#3168;",
            "a" : "",
            "aa" : "&#3134;",
            "A" : "&#3134;",
            "i" : "&#3135;",
            "ee" : "&#3136;",
            "I" : "&#3136;",
            "u" : "&#3137;",
            "oo" : "&#3138;",
            "U" : "&#3138;",
            "e" : "&#3142;",
            "ae" : "&#3143;",
            "E" : "&#3143;",
            "ai" : "&#3144;",
            "o" : "&#3146;",
            "oa" : "&#3147;",
            "O" : "&#3147;",
            "au" : "&#3148;",
            "tR" : "&#3139;",
            "TR" : "&#3140;",
            "k" : "&#3093;",
            "K" : "&#3093;",
            "kh" : "&#3094;",
            "Kh" : "&#3094;",
            "g" : "&#3095;",
            "gh" : "&#3096;",
            "G" : "&#3097;",
            "ch" : "&#3098;",
            "Ch" : "&#3099;",
            "j" : "&#3100;",
            "jh" : "&#3101;",
            "J" : "&#3101;",
            "Jh" : "&#3101;",
            "nY" : "&#3102;",
            "t" : "&#3103;",
            "T" : "&#3104;",
            "d" : "&#3105;",
            "D" : "&#3106;",
            "N" : "&#3107;",
            "th" : "&#3108;",
            "Th" : "&#3109;",
            "dh" : "&#3110;",
            "Dh" : "&#3111;",
            "n" : "&#3112;",
            "p" : "&#3114;",
            "ph" : "&#3115;",
            "b" : "&#3116;",
            "B" : "&#3117;",
            "bh" : "&#3117;",
            "Bh" : "&#3117;",
            "m" : "&#3118;",
            "y" : "&#3119;",
            "r" : "&#3120;",
            "R" : "&#3121;",
            "l" : "&#3122;",
            "L" : "&#3123;",
            "v" : "&#3125;",
            "sh" : "&#3126;",
            "Sh" : "&#3127;",
            "s" : "&#3128;",
            "h" : "&#3129;",
            "AO" : "&#3073;",
            "M" : "&#3074;",
            "H" : "&#3075;",
            "~AO" : "&#3073;",
            "~M" : "&#3074;",
            "~H" : "&#3075;",
            "*" : "&#3149;"
        }


        function split_word(word)
        {
            var syllables = new Array(0);
            var vowel_start_p = true;
            while (word.length) {
                re = new RegExp(vowels);
                var index = word.search(vowels);
                if (index == 0) {  //the vowel's at the start of word
                    var matched = re.exec(word)[0]; //what is it?
                    if (vowel_start_p) {
                        syllables.push(("~"+matched)); //one more to the syllables
                    } else {
                        syllables.push(matched);
                    }
                    vowel_start_p = true;
                    word = word.substring(matched.length);
                } else {
                    re = new RegExp(consonants);
                    var index = word.search(consonants);
                    if (index == 0) {
                        var matched = re.exec(word)[0];
                        syllables.push(matched);
                        vowel_start_p = false;
                        word = word.substring(matched.length);

                        //look ahead for virama setting
                        var next = word.search(vowels);
                        if (next != 0 || word.length == 0)
                            syllables.push('*');
                    } else {
                        syllables.push(word.charAt(0));
                        word = word.substring(1);
                    }
                }
            }
            return syllables;
        }

        function match_code(syllable_mcc)
        {
            var matched = letter_codes[syllable_mcc];

            if (matched != null) return matched;
            return syllable_mcc;
        }

        function one_word(word_ow)
        {
            if (!word_ow) return "";
            var syllables_ow = split_word(word_ow);
            var letters_ow = new Array(0);

            for (var i_ow = 0; i_ow < syllables_ow.length; i_ow++) {
                letters_ow.push(match_code(syllables_ow[i_ow]));
            }
            return letters_ow.join("");
        }

        function many_words(sentence)
        {
            var regex = "((" + vowels + ")|(" + consonants + "))+";
            var words = new Array(0);
            while (sentence.length >= 1) {
                re = new RegExp("^``" + regex);
                var match = re.exec(sentence);
                if (match != null) {
                    match = match[0];
                    words.push("`");
                    words.push(one_word(match.substring(2)));
                    sentence = sentence.substring(match.length);
                } else {
                    re = new RegExp("^`" + regex);
                    match = re.exec(sentence);
                    if (match != null) {
                        match = match[0];
                        words.push(match.substring(1));
                        sentence = sentence.substring(match.length);
                    } else {
                        re = new RegExp("^" + regex);
                        match = re.exec(sentence);
                        if (match != null) {
                            match = match[0];
                            words.push(one_word(match));
                            sentence = sentence.substring(match.length);
                        } else {
                            words.push(sentence.charAt(0));
                            sentence = sentence.substring(1);
                        }
                    }
                }
            }
            return words.join("");
        }

        function surname_many_words(index_pmw)
        {
            var text_pmw = many_words(document.NameRelationChangeForm.surnameenglish.value);

            var ans = "";
            while (text_pmw.length) {
                var unicode_chars = /&#[0-9]+;/;
                re = unicode_chars;
                var matche = re.exec(text_pmw);
                if (matche != null) {
                    matche = matche[0];
                    search = text_pmw.search(unicode_chars);
                    ans += text_pmw.substring(0, search);
                    ans += String.fromCharCode(matche.match(/[0-9]+/));
                    text_pmw = text_pmw.substring(search + matche.length);
                } else {
                    ans += text_pmw.substring(0);
                    text_pmw = "";
                }
            }

            document.NameRelationChangeForm.surnametelugu.value = ans;

            var html_txt = "";
            for (i=0; i<ans.length; i++) {
                var unicode_character = ans.charCodeAt(i);
                switch (unicode_character) {
                    case 32:
                        html_txt += " ";
                        break;
                    case 10:
                    case 13:
                        html_txt += "<br/>\n";
                        break;
                    default:
                        html_txt += "&#" + unicode_character + ";";
                }
            }

            document.NameRelationChangeForm.telugupersonname.value = html_txt;
        }

    </script>


    <!--Telugu Father name-->
    <script>

        function description()
        {

            document.NameRelationChangeForm.txtDescription.value=document.NameRelationChangeForm.telugu.value;
        }
        function Catname()
        {

            document.NameRelationChangeForm.txtCatName.value=document.NameRelationChangeForm.telugu.value;
        }
        function Flashnews()
        {

            document.NameRelationChangeForm.txtFlashNews.value=document.NameRelationChangeForm.telugu.value;
        }
        function Videos()
        {

            document.NameRelationChangeForm.txthvsubject.value=document.NameRelationChangeForm.telugu.value;
        }
        function  description()
        {
            document.NameRelationChangeForm.txthvdescription.value=document.NameRelationChangeForm.telugu.value;
        }
        var vowels = "(A(O)?)|(E)|(H)|(I)|(M)|(O)|(TR)|(U)|(a((a)|(e)|(i)|(u))?)|(e(e)?)|(i)|(o((a)|(o))?)|(tR)|(u)"
        var consonants = "(B(h)?)|(Ch)|(D(h)?)|(G)|(J(h)?)|(K(h)?)|(L)|(N)|(R)|(Sh)|(T(h)?)|(b(h)?)|(ch)|(d(h)?)|(g(h)?)|(h)|(j(h)?)|(k(h)?)|(l)|(m)|(n(Y)?)|(p(h)?)|(r)|(s(h)?)|(t(h)?)|(v)|(y)"
        var letter_codes = {
            "~a" : "&#3077;",
            "~aa" : "&#3078;",
            "~A" : "&#3078;",
            "~i" : "&#3079;",
            "~ee" : "&#3080;",
            "~I" : "&#3080;",
            "~u" : "&#3081;",
            "~oo" : "&#3082;",
            "~U" : "&#3082;",
            "~e" : "&#3086;",
            "~ae" : "&#3087;",
            "~E" : "&#3087;",
            "~ai" : "&#3088;",
            "~o" : "&#3090;",
            "~oa" : "&#3091;",
            "~O" : "&#3091;",
            "~au" : "&#3092;",
            "~tR" : "&#3083;",
            "~TR" : "&#3168;",
            "a" : "",
            "aa" : "&#3134;",
            "A" : "&#3134;",
            "i" : "&#3135;",
            "ee" : "&#3136;",
            "I" : "&#3136;",
            "u" : "&#3137;",
            "oo" : "&#3138;",
            "U" : "&#3138;",
            "e" : "&#3142;",
            "ae" : "&#3143;",
            "E" : "&#3143;",
            "ai" : "&#3144;",
            "o" : "&#3146;",
            "oa" : "&#3147;",
            "O" : "&#3147;",
            "au" : "&#3148;",
            "tR" : "&#3139;",
            "TR" : "&#3140;",
            "k" : "&#3093;",
            "K" : "&#3093;",
            "kh" : "&#3094;",
            "Kh" : "&#3094;",
            "g" : "&#3095;",
            "gh" : "&#3096;",
            "G" : "&#3097;",
            "ch" : "&#3098;",
            "Ch" : "&#3099;",
            "j" : "&#3100;",
            "jh" : "&#3101;",
            "J" : "&#3101;",
            "Jh" : "&#3101;",
            "nY" : "&#3102;",
            "t" : "&#3103;",
            "T" : "&#3104;",
            "d" : "&#3105;",
            "D" : "&#3106;",
            "N" : "&#3107;",
            "th" : "&#3108;",
            "Th" : "&#3109;",
            "dh" : "&#3110;",
            "Dh" : "&#3111;",
            "n" : "&#3112;",
            "p" : "&#3114;",
            "ph" : "&#3115;",
            "b" : "&#3116;",
            "B" : "&#3117;",
            "bh" : "&#3117;",
            "Bh" : "&#3117;",
            "m" : "&#3118;",
            "y" : "&#3119;",
            "r" : "&#3120;",
            "R" : "&#3121;",
            "l" : "&#3122;",
            "L" : "&#3123;",
            "v" : "&#3125;",
            "sh" : "&#3126;",
            "Sh" : "&#3127;",
            "s" : "&#3128;",
            "h" : "&#3129;",
            "AO" : "&#3073;",
            "M" : "&#3074;",
            "H" : "&#3075;",
            "~AO" : "&#3073;",
            "~M" : "&#3074;",
            "~H" : "&#3075;",
            "*" : "&#3149;"
        }


        function split_word(word)
        {
            var syllables = new Array(0);
            var vowel_start_p = true;
            while (word.length) {
                re = new RegExp(vowels);
                var index = word.search(vowels);
                if (index == 0) {  //the vowel's at the start of word
                    var matched = re.exec(word)[0]; //what is it?
                    if (vowel_start_p) {
                        syllables.push(("~"+matched)); //one more to the syllables
                    } else {
                        syllables.push(matched);
                    }
                    vowel_start_p = true;
                    word = word.substring(matched.length);
                } else {
                    re = new RegExp(consonants);
                    var index = word.search(consonants);
                    if (index == 0) {
                        var matched = re.exec(word)[0];
                        syllables.push(matched);
                        vowel_start_p = false;
                        word = word.substring(matched.length);

                        //look ahead for virama setting
                        var next = word.search(vowels);
                        if (next != 0 || word.length == 0)
                            syllables.push('*');
                    } else {
                        syllables.push(word.charAt(0));
                        word = word.substring(1);
                    }
                }
            }
            return syllables;
        }

        function match_code(syllable_mcc)
        {
            var matched = letter_codes[syllable_mcc];

            if (matched != null) return matched;
            return syllable_mcc;
        }

        function one_word(word_ow)
        {
            if (!word_ow) return "";
            var syllables_ow = split_word(word_ow);
            var letters_ow = new Array(0);

            for (var i_ow = 0; i_ow < syllables_ow.length; i_ow++) {
                letters_ow.push(match_code(syllables_ow[i_ow]));
            }
            return letters_ow.join("");
        }

        function many_words(sentence)
        {
            var regex = "((" + vowels + ")|(" + consonants + "))+";
            var words = new Array(0);
            while (sentence.length >= 1) {
                re = new RegExp("^``" + regex);
                var match = re.exec(sentence);
                if (match != null) {
                    match = match[0];
                    words.push("`");
                    words.push(one_word(match.substring(2)));
                    sentence = sentence.substring(match.length);
                } else {
                    re = new RegExp("^`" + regex);
                    match = re.exec(sentence);
                    if (match != null) {
                        match = match[0];
                        words.push(match.substring(1));
                        sentence = sentence.substring(match.length);
                    } else {
                        re = new RegExp("^" + regex);
                        match = re.exec(sentence);
                        if (match != null) {
                            match = match[0];
                            words.push(one_word(match));
                            sentence = sentence.substring(match.length);
                        } else {
                            words.push(sentence.charAt(0));
                            sentence = sentence.substring(1);
                        }
                    }
                }
            }
            return words.join("");
        }

        function first_many_words(index_pmw)
        {
            var text_pmw = many_words(document.NameRelationChangeForm.firstnameenglish.value);

            var ans = "";
            while (text_pmw.length) {
                var unicode_chars = /&#[0-9]+;/;
                re = unicode_chars;
                var matche = re.exec(text_pmw);
                if (matche != null) {
                    matche = matche[0];
                    search = text_pmw.search(unicode_chars);
                    ans += text_pmw.substring(0, search);
                    ans += String.fromCharCode(matche.match(/[0-9]+/));
                    text_pmw = text_pmw.substring(search + matche.length);
                } else {
                    ans += text_pmw.substring(0);
                    text_pmw = "";
                }
            }

            document.NameRelationChangeForm.firstnametelugu.value = ans;

            var html_txt = "";
            for (i=0; i<ans.length; i++) {
                var unicode_character = ans.charCodeAt(i);
                switch (unicode_character) {
                    case 32:
                        html_txt += " ";
                        break;
                    case 10:
                    case 13:
                        html_txt += "<br/>\n";
                        break;
                    default:
                        html_txt += "&#" + unicode_character + ";";
                }
            }

            document.NameRelationChangeForm.telugufathername.value = html_txt;
        }


        function isAlpha(keyCode,name)
        {
            if (keyCode == 16)
                isShift = true;
            var str = name.value;
            if(str.substring(0,1)==" ")
            {
                name.value ="";
                name.focus();
                return ((keyCode >= 65 && keyCode <= 90) || keyCode == 8 || keyCode == 9 || keyCode == 37 || keyCode == 39 || keyCode == 46)
            }else{
                return ((keyCode >= 65 && keyCode <= 90) || keyCode == 8 || keyCode == 32 || keyCode == 9 || keyCode == 37 || keyCode == 39 || keyCode == 46)
            }
        }


        function messagedisplay1(id,name){

            if (id==1) id = "This Field Allow Numbers and Alphabets Only";
            if (id==2) id = "This Field Allow Alphabets Only";
            if (id==3) id = "This Field Allow Numbers Only";
            document.getElementById(name).onmouseover = function(){
                third2('<table cellpadding=3 border=0><td class=messagelablel>'+id+'</td></table>', '', 244);
            }
            document.getElementById(name).onmouseout = function(){
                first4();
            }
        }

        function modelesswin(url,mwidth,mheight){
            if (document.all&&window.print) //if ie5
                eval('window.showModelessDialog(url,"devi","help:0;resizable:1;dialogWidth:'+mwidth+'px;dialogHeight:'+mheight+'px")')
            else
                eval('window.open(url,"devi","width='+mwidth+'px,height='+mheight+'px,resizable=0,scrollbars=0")')
        }

    </script>
    <script type="text/javascript" language="javascript">
        //oldParticularId

        function updateDetails(){
          

            var list = <%=oldParticularId%>;
            var flag = false;
            var multipleId = "";
            if(document.forms[0].biometricId.checked==false){
                alert("please Select Any One!");
                flag= true;
                return false;

            }
            if(list==1 && flag==false){


                var len = document.forms[0].biometricId.checked;
                var elems = document.forms[0].biometricId;
                if(len>0){

                    for(var i=0;i<len;i++){

                        multipleId =elems.value;

                        if(multipleId=="1"){

                            if(document.forms[0].elements['surnameenglish'].value=="") {
                                alert("Please Enter FullName in Telugu!!");
                                document.forms[0].elements['surnameenglish'].focus();
                                return false;
                            }
                        }else if(multipleId=="5"){

                            if(document.forms[0].elements['surnameenglish'].value=="") {
                                alert("Please Enter FullName in Telugu!!");
                                document.forms[0].elements['surnameenglish'].focus();
                                return false;
                            }
                        } else if(multipleId=="2"){
                            if(document.forms[0].elements['populatenewRelationName'].value=="") {
                                alert("Please Enter Relation Name!!");
                                document.forms[0].elements['populatenewRelationName'].focus();
                                return false;
                            } else if(document.forms[0].elements['grelation'].value=="") {
                                alert("Please Select Relation!!");
                                document.forms[0].elements['grelation'].focus();
                                return false;
                            } else if(document.forms[0].elements['firstnameenglish'].value=="") {
                                alert("Please Enter RelationName in Telugu!!");
                                document.forms[0].elements['firstnameenglish'].focus();
                                return false;
                            } else  if(document.forms[0].elements['firstnameenglish'].value=="") {
                                alert("Please Enter Relationname!");
                                document.forms[0].elements['firstnameenglish'].focus();
                                return false;
                            }
                        }else if(multipleId=="3"){
                          
                            if(document.forms[0].elements['populatenewMolesOne'].value==""){
                                alert("Please Enter New MolesOne");
                                document.forms[0].elements['populatenewMolesOne'].focus();
                                return false;
                            }else if(document.forms[0].elements['populatenewMolesTwo'].value==""){
                                alert("Please Enter New MolesTwo");
                                document.forms[0].elements['populatenewMolesTwo'].focus();
                                return false;
                            }
                        }
                        else if(multipleId=="4"){
                            if(document.forms[0].elements['populateNewDOB'].value==""){
                                alert("Please Enter DateOF Birth");
                                document.forms[0].elements['populateNewDOB'].focus();
                                return false;
                            }else {
                                var validFormat =/^\d{2}\/\d{2\/\d{4}$/;
                                var returnVal = false;
                                var cc = document.forms[0].elements['populateNewDOB'].value;
                                var datFiled = cc.split("/")[0];
                                var monthFiled = cc.split("/")[1];
                                var yearFiled = cc.split("/")[2];
                                var dayObj = new Date(yearFiled,monthFiled-1,datFiled);
                                if((dayObj.getDate()!=datFiled)  || (dayObj.getMonth()+1!=monthFiled) || (dayObj.getFullYear() !=yearFiled)){
                                    alert(" Please Enter Valid Format DD/MM/YYYY!");
                                    if(document.forms[0].elements['populateNewDOB'].value){
                                        document.forms[0].elements['populateNewDOB'].value ="";
                                        return false;
                                    }
                                }
                            }

                            if ( "1"==<%=dOBDetails%>){
                                var dobList=confirm("Percentage will change while updating Date of Birth");
                                if(dobList==true) {
                                    document.forms[0].mode.value="updateDetails";
                                    document.forms[0].submit();
                                }else {
                                    if(dataForm==false) {
                                        document.forms[0].mode.value="notUpdateDetails";
                                        document.forms[0].submit();
                                   
                                    }
                                }
                            } 
                        }          
                    }if(dataForm==false) {
                        document.forms[0].mode.value="updateDetails";
                        document.forms[0].submit();
                    }else {
                        document.forms[0].mode.value="updateDetails";
                        document.forms[0].submit();
                    }

                }
            }else {
                var len = document.forms[0].biometricId.length;
                var choosen = "";
                var elems = document.forms[0].biometricId;
                var dataForm =true;
                var checkId =true;
                if(len>0){
                    for(var i=1;i<len;i++){
                        if(elems[i].checked){
                         
                            choosen = elems[i].value;
                       

                            if(elems[i].value=="1"){
                                choosen =="1";
                          
                                if(document.forms[0].elements['populateOldNewName'].value=="") {
                                    alert("Please Enter SurName!!");
                                    document.forms[0].elements['populateOldNewName'].focus();
                                    return false;
                               
                                }
                                else if(document.forms[0].elements['populatenewName'].value=="") {
                                    alert("Please Enter Name!!");
                                    document.forms[0].elements['populatenewName'].focus();
                                    return false;
                                
                                }
                                else if(document.forms[0].elements['surnameenglish'].value=="") {
                                    alert("Please Enter SurName in Telugu!!");
                                    document.forms[0].elements['surnameenglish'].focus();
                                    return false;
                                }
                            }else if(elems[i].value=="2"){
                                choosen == "2";
                                if(document.forms[0].elements['populatenewRelationName'].value=="") {
                                    alert("Please Enter Relation Name!!");
                                    document.forms[0].elements['populatenewRelationName'].focus();
                                    return false;
                                    dataForm = true;
                                } else if(document.forms[0].elements['grelation'].value=="") {
                                    alert("Please Select Relation!!");
                                    document.forms[0].elements['grelation'].focus();
                                    return false;
                                    dataForm = true;
                                } else if(document.forms[0].elements['firstnameenglish'].value=="") {
                                    alert("Please Enter RelationName in Telugu!!");
                                    document.forms[0].elements['firstnameenglish'].focus();
                                    return false;
                                    dataForm = true;
                                } else  if(document.forms[0].elements['firstnameenglish'].value=="") {
                                    alert("Please Enter Relationname!");
                                    document.forms[0].elements['firstnameenglish'].focus();
                                    dataForm = true;
                                    return false;
                               
                                }
                            }else if(elems[i].value=="3"){
                                choosen =="3";
                                if(document.forms[0].elements['populatenewMolesOne'].value==""){
                                    alert("Please Enter New MolesOne");
                                    document.forms[0].elements['populatenewMolesOne'].focus();
                                    return false;
                                    dataForm = true;
                                }else if(document.forms[0].elements['populatenewMolesTwo'].value==""){
                                    alert("Please Enter New MolesTwo");
                                    document.forms[0].elements['populatenewMolesTwo'].focus();
                                    return false;
                                    dataForm = true;
                                }
                            }else if(elems[i].value=="4"){
                                choosen =="4";

                                if(document.forms[0].elements['populateNewDOB'].value==""){
                                    alert("Please Enter DateOF Birth");
                                    document.forms[0].elements['populateNewDOB'].focus();
                                    return false;
                                    dataForm = true;
                                }else {
                                    var validFormat =/^\d{2}\/\d{2\/\d{4}$/;
                                    var returnVal = false;
                                    var cc = document.forms[0].elements['populateNewDOB'].value;
                                    var datFiled = cc.split("/")[0];
                                    var monthFiled = cc.split("/")[1];
                                    var yearFiled = cc.split("/")[2];
                                    var dayObj = new Date(yearFiled,monthFiled-1,datFiled);
                                    if((dayObj.getDate()!=datFiled)  || (dayObj.getMonth()+1!=monthFiled) || (dayObj.getFullYear() !=yearFiled)){
                                        alert(" Please Enter Valid Format DD/MM/YYYY!");
                                        if(document.forms[0].elements['populateNewDOB'].value){
                                            document.forms[0].elements['populateNewDOB'].value ="";
                                            return false;
                                            // dataForm = true;
                                        }
                                    }
                                }

                                if ("1"==<%=dOBDetails%>){
                                    var dobList=confirm("Percentage will change while updating Date of Birth");
                                    if(dobList==true) {
                                    
                                        document.forms[0].mode.value="updateDetails";
                                        document.forms[0].submit();
                                    }else {
                                        if(dataForm==false){
                                            document.forms[0].mode.value="notUpdateDetails";
                                            document.forms[0].submit();
                                        }
                                    
                                    }
                                }

                            }
                          
                        }
                    }

                }
           
               
             
                if(dataForm==false) {
                    document.forms[0].mode.value="updateDetails";
                    document.forms[0].submit();
                }else {
                    document.forms[0].mode.value="updateDetails";
                    document.forms[0].submit();
                }
                

            }
            
        }
    </script>

    <body>
        <!-- <tr>
             <td><img src="DisabilityUITG/images/Header_3.jpg"></td>
         </tr> -->
        <!-- <tr>
             <td><hr></td> requestFormId personCodeFormId
         </tr> -->
        <html:form  action="/nameRelationChange">
            <input type="hidden" name="mode"/>
            <html:hidden property="requestFormId" value="<%=requestFormId%>"/>
            <html:hidden property="personCodeFormId" value="<%=personCodeFormId%>"/>
            <html:hidden property="emailRequestId" value="<%=requestId%>"/>
            <html:hidden property="emailPersonCode" value="<%=personCode%>"/>



            <logic:present name="msg">
                <p id="succmsg">${msg}</p>
            </logic:present>

            <%--<logic:present name="dOBDetails">
                <script>

                    var dobList = confirm("${dOBDetails}");
                    if(dobList==true) {
                        document.forms[0].mode.value="updateRequestDataDetails";
                        document.forms[0].submit();

                    }else{
                     document.forms[0].mode.value="updateRequestDataDetails";
                     document.forms[0].submit();
                         }


                </script>
            </logic:present>--%>
            <p>Personal Information</p>
            <table cellpadding="0" cellspacing="1" align="center" width="90%"   class="inputform">
                <logic:notEmpty name="nameList">
                    <tr>
                        <td valign="top">
                            <table cellpadding="0" cellspacing="1" align="center" width="100%"  border="0" class="inputform">
                                <tr>
                                    <th>
                                        Action
                                    </th>
                                    <th>
                                        SADAREM ID
                                    </th>
                                    <th colspan="3">
                                        Old Name
                                    </th>

                                    <th colspan="3">
                                        New Name
                                    </th>


                                </tr>
                                <logic:iterate name="nameList" id="details">
                                    <tr>
                                    <input type="hidden" name="requestPersonCodeData" value="<bean:write name="details" property="requestpersonCode"/>"/>
                                    <input type="hidden" name="requestIndividualRequestTypeId" value="<bean:write name="details" property="requestIndividualTyepId"/>"/>
                                    <input type="hidden" name="particularRequestIdDetails" value="<bean:write name="details" property="requestChangeId"/>"/>

                                    <logic:notEmpty name="details" property="oldName">
                                        <td width="75px" style="text-align:center">
                                            <input type="checkbox" name="biometricId" value="<bean:write name="details" property="requestIndividualTyepId"/>"/>
                                            <input type="hidden" name="hiddenId" value="<bean:write name="details" property="requestIndividualTyepId"/>"/>
                                        </td>
                                        <td>
                                            <bean:write name="details" property="requestpersonCode"/>
                                        </td>
                                        <td colspan="3">
                                            <input type="text" name="populateOldName" value="<bean:write  name="details" property="oldName"/>" readonly="true" style="width:200px"/>
                                        </td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="details" property="newName">
                                        <td colspan="3">
                                            <input type="text" name="populatenewName" value="<bean:write  name="details" property="newName"/>" readonly="true" style="width:200px"/>
                                        </td>
                                    </logic:notEmpty>


                        </tr>
                    </logic:iterate>

                    <tr>
                        <td>
                            Old Telugu Name
                        </td>
                        <logic:notEmpty name="details" property="oldTeluguName">
                            <td>
                                <input type="text" name="oldTeluguName" value="${details.oldTeluguName}" readonly="true"/>

                            </td>
                        </logic:notEmpty>


                        <td><b><font color="red"><%=personname%></font></b></td>
                        <td>
                            <input type="text"   onkeyup="javascript1:surname_many_words()" onfocus="javascript1:surname_many_words()"
                                   name="surnameenglish" maxlength="30" onkeydown="return isAlpha(event.keyCode,surnameenglish);" onkeypress="return space(event,this)"
                                   onmouseover="messagedisplay(2,'surnameenglish')" />
                        </td>
                        <td><b><font color="red"><%=telugu%></font></b></td>
                        <td>
                            <html:text  property="surnametelugu"  readonly="true"/>
                            <a href="javascript:modelesswin('./DisabilityUITG/images/TeluguKeyBoard.JPG',700,300)">TeluguKeyBoard</a>
                            <input type="hidden" id="telugu" name="telugupersonname" />
                        </td>

                    </tr>
                </table>
            </td>
        </tr>
    </logic:notEmpty>

    <logic:notEmpty name="surNameList">
    <tr>
        <td valign="top">
            <table cellpadding="0" cellspacing="1" align="center" width="100%"  border="0" class="inputform" >
                <tr>
                    <th>
                        Action
                    </th>
                    <th>
                        SADAREM ID
                    </th>
                    <th colspan="2">
                        Old Surname
                    </th>
                    <th colspan="2">
                        New Surname
                    </th>

                </tr>

                <logic:iterate name="surNameList" id="details">
                    <tr>
                    <input type="hidden" name="requestPersonCodeData" value="<bean:write name="details" property="requestpersonCode"/>"/>
                    <input type="hidden" name="requestIndividualRequestTypeId" value="<bean:write name="details" property="requestIndividualTyepId"/>"/>
                    <input type="hidden" name="particularRequestIdDetails" value="<bean:write name="details" property="requestChangeId"/>"/>


                    <td width="75px" style="text-align:center">
                        <input type="checkbox" name="biometricId" value="<bean:write name="details" property="requestIndividualTyepId"/>"/>
                        <input type="hidden" name="hiddenId" value="<bean:write name="details" property="requestIndividualTyepId"/>"/>
                    </td>
                    <td>
                        <bean:write name="details" property="requestpersonCode"/>
                    </td>
                    <logic:notEmpty name="details" property="oldsurName">
                        <td colspan="2" style="text-align: center">
                            <input type="text" name="populateOldSurName" value="<bean:write  name="details" property="oldsurName"/>" readonly="true" style="width:200px"/>
                        </td>

                    </logic:notEmpty>

                    <logic:empty name="details" property="oldsurName">
                        <td colspan="2" style="text-align: center">
                            <input type="text" name="populateOldSurName" value="" readonly="true" style="width:200px"/>
                        </td>
                    </logic:empty>

                    <logic:notEmpty name="details" property="newsurName">
                        <td colspan="2" style="text-align: center">
                            <input type="text" name="populateOldNewName" value="<bean:write  name="details" property="newsurName"/>" readonly="true" style="width:200px"/>
                        </td>

                    </logic:notEmpty>

        </tr>
    </logic:iterate>

    <tr>
        <td>
            Old Telugu Name
        </td>
        <logic:notEmpty name="details" property="oldTeluguName">
            <td>
                <input type="text" name="oldTeluguName" value="${details.oldTeluguName}" readonly="true"/>

            </td>
        </logic:notEmpty>
        <td><b><font color="red"><%=personname%></font></b></td>
        <td>
            <input type="text"   onkeyup="javascript1:surname_many_words()" onfocus="javascript1:surname_many_words()"
                   name="surnameenglish" maxlength="30" onkeydown="return isAlpha(event.keyCode,surnameenglish);" onkeypress="return space(event,this)"
                   onmouseover="messagedisplay(2,'surnameenglish')" />
        </td>
        <td><b><font color="red"><%=telugu%></font></b></td>
        <td>
            <html:text  property="surnametelugu"  readonly="true"/>
            <a href="javascript:modelesswin('./DisabilityUITG/images/TeluguKeyBoard.JPG',700,300)">TeluguKeyBoard</a>
            <input type="hidden" id="telugu" name="telugupersonname" />
        </td>
    </tr>
</table>
</td>
</tr>
</logic:notEmpty>

<logic:notEmpty name="relationList">
    <tr>
        <td valign="top">
            <table cellpadding="0" cellspacing="1" align="center" width="100%" class="inputform" border="0">
                <tr>
                    <th>
                        Action
                    </th>
                    <th>
                        SADAREM ID
                    </th>
                    <th>
                        Old Relation Name
                    </th>
                    <th>
                        New Relation Name
                    </th>
                    <th >
                        Relation
                    </th>

                </tr>
                <logic:iterate name="relationList" id="details">
                    <tr>
                    <input type="hidden" name="requestPersonCodeData" value="<bean:write name="details" property="requestpersonCode"/>"/>
                    <input type="hidden" name="requestIndividualRequestTypeId" value="<bean:write name="details" property="requestIndividualTyepId"/>"/>
                    <input type="hidden" name="particularRequestIdDetails" value="<bean:write name="details" property="requestChangeId"/>"/>




                    <td width="75px" style="text-align:center">
                        <input type="checkbox" name="biometricId" value="<bean:write name="details" property="requestIndividualTyepId"/>"/>
                        <input type="hidden" name="hiddenId" value="<bean:write name="details" property="requestIndividualTyepId"/>"/>

                    </td>
                    <td>
                        <bean:write name="details" property="requestpersonCode"/>
                    </td>

                    <logic:notEmpty name="details" property="oldRelationName">
                        <td>
                            <input type="text" name="populateOldRelationName" value="<bean:write  name="details" property="oldRelationName"/>" readonly="true"/>


                        </td>

                    </logic:notEmpty>

                    <logic:empty name="details" property="oldRelationName">
                        <td>
                            <input type="text" name="populateOldRelationName" value="" readonly="true"/>
                        </td>
                    </logic:empty>
                    <logic:notEmpty name="details" property="newRelationName">
                        <td>
                            <input type="text" name="populatenewRelationName" value="<bean:write  name="details" property="newRelationName"/>" readonly="true" />
                        </td>


                        <td colspan="2">
                            <html:select property="grelation">
                                <html:option value="">--SELECT--</html:option>
                                <html:option value="1">Father</html:option>
                                <html:option value="2">Mother</html:option>
                                <html:option value="3">Husband</html:option>
                                <html:option value="7">Wife</html:option>
                                <html:option value="5">Brother</html:option>
                                <html:option value="6">Sister</html:option>
                                <html:option value="4">Guardian</html:option>
                            </html:select>
                        </td>

                    </logic:notEmpty>

        </tr>
        <tr>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td><font size="2" color="red"><b><%=fathername%></b></font></td>
            <td><input type="text"   onkeyup="javascript1:first_many_words()"
                       onfocus="javascript1:first_many_words()" name="firstnameenglish" maxlength="30"
                       onkeydown="return isAlpha(event.keyCode,firstnameenglish);" onkeypress="return space(event,this)" />
            </td>
            <td><font size="2" color="red"><b> <%=telugu%></b></font></td>
            <td>
                <html:text   property="firstnametelugu"  readonly="true"/>
                <a href="javascript:modelesswin('./DisabilityUITG/images/TeluguKeyBoard.JPG',700,300)">TeluguKeyBoard</a>
                <input type="hidden" id="telugu" name="telugufathername" />
            </td>
        </tr>
    </logic:iterate>
</table>
</td>
</tr>
</logic:notEmpty>

<logic:notEmpty name="molesList">

    <tr>
        <td valign="top">
            <table cellpadding="0" cellspacing="0" align="center" width="100%" class="inputform" border="0">
                <tr>
                    <th>
                        Action
                    </th>
                    <th>
                        SADAREM ID
                    </th>
                    <th>
                        Old MolesOne
                    </th>
                    <th>
                        Old MolesTwo
                    </th>

                    <th>
                        New MolesOne
                    </th>
                    <th>
                        New MolesTwo
                    </th>

                </tr>
                <logic:iterate name="molesList" id="details">
                    <tr>
                    <input type="hidden" name="requestPersonCodeData" value="<bean:write name="details" property="requestpersonCode"/>"/>
                    <input type="hidden" name="requestIndividualRequestTypeId" value="<bean:write name="details" property="requestIndividualTyepId"/>"/>
                    <input type="hidden" name="particularRequestIdDetails" value="<bean:write name="details" property="requestChangeId"/>"/>



                    <td width="75px" style="text-align:center">
                        <input type="checkbox" name="biometricId" value="<bean:write name="details" property="requestIndividualTyepId"/>"/>
                        <input type="hidden" name="hiddenId" value="<bean:write name="details" property="requestIndividualTyepId"/>"/>

                    </td>
                    <td>
                        <bean:write name="details" property="requestpersonCode"/>
                    </td>
                    <logic:notEmpty name="details" property="oldmolesOne">
                        <td>
                            <input type="text" name="populateoldMolesOne" value="<bean:write  name="details" property="oldmolesOne"/>" readonly="true"/>
                        </td>

                    </logic:notEmpty>
                    <logic:notEmpty name="details" property="oldmolesTwo">
                        <td>
                            <input type="text" name="populateoldMolesTwo" value="<bean:write  name="details" property="oldmolesTwo"/>" readonly="true"/>
                        </td>
                    </logic:notEmpty>


                    <logic:notEmpty name="details" property="newmoleOne">
                        <td>
                            <input type="text" name="populatenewMolesOne" value="<bean:write  name="details" property="newmoleOne"/>"/>
                        </td>

                    </logic:notEmpty>

                    <logic:notEmpty name="details" property="newmolesTwo">
                        <td>
                            <input type="text" name="populatenewMolesTwo" value="<bean:write  name="details" property="newmolesTwo"/>"/>
                        </td>

                    </logic:notEmpty>
        </tr>
    </logic:iterate>
</table>
</td>
</tr>
</logic:notEmpty>

<logic:notEmpty name="dateOfBirthList">
    <tr>
        <td valign="top">
            <table cellpadding="0" cellspacing="1" align="center" width="100%" border="0" class="inputform" >
                <tr>
                    <td>
                        Action
                    </td>
                    <td>
                        SADAREM ID
                    </td>
                    <td>
                        Old DateOfBirth  [DD/MM/YYYY]
                    </td>
                    <td colspan="3">
                        New DateOfBirth  [DD/MM/YYYY]
                    </td>
                </tr>
                <logic:iterate name="dateOfBirthList" id="details">

                    <input type="hidden" name="requestPersonCodeData" value="<bean:write name="details" property="requestpersonCode"/>"/>
                    <input type="hidden" name="requestIndividualRequestTypeId" value="<bean:write name="details" property="requestIndividualTyepId"/>"/>
                    <input type="hidden" name="particularRequestIdDetails" value="<bean:write name="details" property="requestChangeId"/>"/>
                    <tr>

                        <td width="75px" style="text-align:center">
                            <input type="checkbox" name="biometricId" value="<bean:write name="details" property="requestIndividualTyepId"/>"/>
                        </td>
                        <td>
                            <bean:write name="details" property="requestpersonCode"/>
                        </td>
                        <logic:notEmpty name="details" property="oldDOB">
                            <td>
                                <input type="text" name="populateOldDOB" value="<bean:write  name="details" property="oldDOB"/>" readonly="true"/>
                            </td>
                        </logic:notEmpty>
                        <logic:notEmpty name="details" property="newDOB">
                            <td>
                                <input type="text" name="populateNewDOB" value="<bean:write  name="details" property="newDOB"/>" />
                            </td>
                        </logic:notEmpty>

                    </tr>
                </logic:iterate>
            </table>
        </td>
    </tr>
</logic:notEmpty>

<logic:notPresent name="msg">
    <tr>

        <th colspan="6" align="center">
            <html:button property="but" value="Update" onclick="return updateDetails();"/>
        </th>

    </tr>
</logic:notPresent>

</table>
<br/>
<table cellpadding="0" cellspacing="0" align="center" width="90%" border="0" class="inputform" >
    <logic:notEmpty name="surNameList">

        <p style="text-align: left"><font size="2" color="red">Note</font>:1)In Telugu Field will display complete Name.Please Enter Surname and Name in Telugu<br/><br/>

        </p>
    </logic:notEmpty>
</table>



<br/>
<table cellpadding="0" cellspacing="0" align="center" width="90%" border="0" class="inputform" >
    <logic:notEmpty name="nameList">

        <p style="text-align: left"><font size="2" color="red">Note</font>:1)In Telugu Field will display complete Name.Please Enter Surname and Name in Telugu<br/><br/>
        </p>
    </logic:notEmpty>
</table>
</html:form>

<script>
    var studentCheck = document.getElementsByName("biometricId");

    for(var i=0;i<studentCheck.length;i++){
        if(studentCheck[i].checked){
            studentCheck[i].checked = false;
        } else {
            studentCheck[i].checked = true;
        }
    }
    var bioIds = document.getElementsByName("biometricId");
    for(var i=0;i<bioIds.length;i++){
        bioIds[i].checked = true;
    }

</script>


<!--<tr>
    <td>
        <div align="center">
            <font face="Arial" size="1" color="#143A27"> Copyrights Reserved by Govt of Telangana &#169; 2010 </font> <br />
            <strong><font face="Arial" size="1" color="#143A27">SADAREM</font>
            </strong> <font face="Arial" size="1" color="#143A27">is hosted & maintained by </font>
            <a href="http://www.aponline.gov.in" target="_blank">
                <font face="Arial" size="1"> APOnline </font> </a> <br />
            <font face="Arial" size="1" color="#143A27"> Site Best viewed in 1024*768 Resolution  </font><br>
            <font face="Arial" size="1" color="#143A27">Best Use in Internet Explorer <font size="2" color="blue">6</font></font>
        </div>
    </td>
</tr> -->

</body>

</html>
