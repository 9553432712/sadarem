
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
  var text_pmw = many_words(document.NewCertificateForm.surnameenglish.value);

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

  document.NewCertificateForm.surnametelugu.value = ans;

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

  document.NewCertificateForm.telugupersonname.value = html_txt;
}
