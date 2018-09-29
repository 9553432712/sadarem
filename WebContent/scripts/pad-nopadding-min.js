/*
CryptoJS v3.1.2
code.google.com/p/crypto-js
(c) 2009-2013 by Jeff Mott. All rights reserved.
code.google.com/p/crypto-js/wiki/License
*/
CryptoJS.pad.NoPadding={pad:function(){},unpad:function(){}};

function padString(source) 
{
    var paddingChar = ' ';
    var size = 16;
    var x = source.length % size;
    var padLength = size - x;
    
    for (var i = 0; i < padLength; i++) source += paddingChar;
    
    return source;
}


function encrypt(message)
{
     var key = CryptoJS.enc.Latin1.parse('0123456789abcdef');
	  var iv  = CryptoJS.enc.Latin1.parse('fedcba9876543210');
    var padMsg = padString(message);
		//alert(message);
    var encrypted = CryptoJS.AES.encrypt(padMsg, key, { iv: iv, padding: CryptoJS.pad.NoPadding, mode: CryptoJS.mode.CBC});

    //alert("Encrypted: "+encrypted);
  return encrypted;
} 
