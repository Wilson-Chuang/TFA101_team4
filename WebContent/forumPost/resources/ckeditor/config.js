/**
 * @license Copyright (c) 2003-2018, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */
CKEDITOR.editorConfig = function( config ) {
//	config.uiColor = '#CCCCFF';

//	config.skin = 'bootstrapck';
	config.skin = 'moono';
//	config.skin = 'moono-dark';
//	config.skin = 'moono-lisa'; 
//	config.skin = 'moonocolor';
//	config.skin = 'office2013'
    
	config.language = 'zh';//zh //en //ja //ko
	config.height = 150;
	config.width = 580;
//	config.resize_maxWidth = 800;
	
	config.extraPlugins = 'dragresize,base64image,base64imagedrop';
    config.image_previewText=' ';
	
    config.toolbarGroups = [
		{ name: 'insert', groups: [ 'base64image', 'insert' ] },
		{ name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align', 'bidi', 'paragraph' ] },
		{ name: 'document', groups: [ 'mode', 'document', 'doctools' ] },
		{ name: 'editing', groups: [ 'find', 'selection', 'spellchecker', 'editing' ] },
		{ name: 'forms', groups: [ 'forms' ] },
		{ name: 'links', groups: [ 'links' ] },
		{ name: 'tools', groups: [ 'tools' ] },
		'/',
		{ name: 'clipboard', groups: [ 'clipboard', 'undo' ] },
		{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
		{ name: 'colors', groups: [ 'colors' ] },
		{ name: 'styles', groups: [ 'styles' ] },
		{ name: 'others', groups: [ 'others' ] },
		{ name: 'about', groups: [ 'about', 'source' ] }
	];

	config.removeButtons = 'About,Save,NewPage,Preview,Print,Templates,CreateDiv,Language,HiddenField,ShowBlocks,Scayt,Textarea,TextField,Flash,SpecialChar,PageBreak,Iframe,ImageButton,Find';
	
};

