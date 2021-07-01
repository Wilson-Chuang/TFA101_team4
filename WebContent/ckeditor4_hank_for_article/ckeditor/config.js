/**
 * @license Copyright (c) 2003-2018, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function( config ) {
//	config.uiColor = '#CCCCFF';

	config.skin = 'bootstrapck';
//	config.skin = 'moono';
//	config.skin = 'moono-dark';
//	config.skin = 'moono-lisa';
//	config.skin = 'moonocolor';
//	config.skin = 'office2013'
    
	config.language = 'zh';//zh //en //ja //ko
	config.height = 500;
//	config.width = 1000;
//	config.resize_maxWidth = 800;
	
	config.extraPlugins = 'dragresize,base64image,base64imagedrop';
    config.image_previewText=' '; /*去掉圖片預覽框的文字*/
	
	config.toolbarGroups = [
		{ name: 'document', groups: [ 'mode', 'document', 'doctools' ] },
		{ name: 'clipboard', groups: [ 'clipboard', 'undo' ] },
		{ name: 'editing', groups: [ 'find', 'selection', 'spellchecker', 'editing' ] },
		{ name: 'forms', groups: [ 'forms' ] },
		{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
		{ name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align', 'bidi', 'paragraph' ] },
		{ name: 'links', groups: [ 'links' ] },
		{ name: 'insert', groups: [ 'insert' ] },
		{ name: 'styles', groups: [ 'styles' ] },
		{ name: 'colors', groups: [ 'colors' ] },
		{ name: 'tools', groups: [ 'tools' ] },
		{ name: 'others', groups: [ 'others' ] },
		{ name: 'about', groups: [ 'about' ] }
	];

	// Remove some buttons provided by the standard plugins, which are
	// not needed in the Standard(s) toolbar.
	config.removeButtons = 'Source,Save,NewPage,Templates,ExportPdf,Preview,Print,Scayt,Checkbox,Form,Radio,TextField,Textarea,Select,Button,ImageButton,HiddenField,CreateDiv,Anchor,Image,Flash,Iframe,PageBreak,About,ShowBlocks';

};
