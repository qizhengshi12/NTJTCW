CKEDITOR.editorConfig = function( config )
{
	config.language = 'zh-cn';
	config.skin = 'office2003';
	//config.width = 800; 
	config.height = 300; 
	config.removePlugins = 'elementspath';
	config.extraPlugins = 'myplugin'; //新建插件
	
	config.toolbar= 

		[ 

		['Preview','-','SelectAll','RemoveFormat','-','Bold','Italic','Underline','Strike','-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','Format','Font','FontSize'], 

		'/', 

		['MyButton']//我的按钮在最后

		]; 

	

};