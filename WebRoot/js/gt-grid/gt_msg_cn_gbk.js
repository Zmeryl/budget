//

if (!window.GT){
	window.GT={};
}
GT.Msg=GT.Msg || {};
GTMsg=GT.Msg;

GT.Msg.Grid = GT.Msg.Grid || {};

GT.Msg.Grid.cn={
	LOCAL	: "CN",
	ENCODING		: "GBK",
	NO_DATA : "û��Ҫչ�ֵ�����...",


	GOTOPAGE_BUTTON_TEXT: '��ת��ָ����ҳ',

	FILTERCLEAR_TEXT: "���ȫ����������",
	SORTASC_TEXT	: "��������",
	SORTDESC_TEXT	: "��������",
	SORTDEFAULT_TEXT: "�������״̬",

	ERR_PAGENUM		: "��תҳ��ֻ���� 1 �� #{1} ������!",

	EXPORT_CONFIRM	: "����ȫ��������?\n\n( \"ȡ��\" Ϊ����ǰҳ��)",
	OVER_MAXEXPORT	: "������������������������ֵ( #{1} �� ).",

	PAGE_STATE	: "�� #{1} - #{2}��, �� #{3}ҳ #{4}������",
	PAGE_STATE_FULL	: "�� #{5}ҳ #{1} - #{2}��, �� #{3}ҳ #{4}������",

	SHADOWROW_FAILED: "�޷�ȡ�������Ϣ",
	NEARPAGE_TITLE	: "",
	WAITING_MSG : '����������,���Ժ�...',

	NO_RECORD_UPDATE: "û�м�¼��Ҫ������.",
	UPDATE_CONFIRM	: "ȷ��Ҫִ�б��������?",
	NO_MODIFIED: "û�м�¼������,���豣��.",

	
	PAGE_BEFORE : '��',
	PAGE_AFTER : 'ҳ',

	PAGESIZE_BEFORE :   'ÿҳ',
	PAGESIZE_AFTER :   '��',

	RECORD_UNIT : '��',
	
	CHECK_ALL : 'ȫѡ',

	COLUMNS_HEADER : '--�� ��--',

	DIAG_TITLE_FILTER : '����ѡ��',
	DIAG_NO_FILTER : '�޹�������',
	TEXT_ADD_FILTER	: "�������",
	TEXT_CLEAR_FILTER	: "�����������",
	TEXT_OK	: "ȷ��",
	TEXT_DEL : "ɾ��",
	TEXT_CANCEL	: "ȡ��",
	TEXT_CLOSE	: "�ر�",
	TEXT_UP : "����",
	TEXT_DOWN : "����",

	NOT_SAVE : "�������޸�,��δ����,Ҫ���ڱ�����? \n �����\"ȡ��\",δ�������Ϣ����ʧ.",

	DIAG_TITLE_CHART  : 'ͼ��',

	CHANGE_SKIN : "����",

	STYLE_NAME_DEFAULT : "Ĭ����ɫ���",
	STYLE_NAME_CHINA : "�й�����",
	STYLE_NAME_VISTA : "vista���",
	STYLE_NAME_MAC : "mac���",

	MENU_FREEZE_COL : "����/���� ��",
	MENU_SHOW_COL : "��ʾ/���� ��",
	MENU_GROUP_COL : "����/����� ��",

	TOOL_RELOAD : "ˢ��" ,
	TOOL_ADD : "���" ,
	TOOL_DEL : "ɾ��" ,
	TOOL_SAVE : "����" ,

	TOOL_PRINT : "��ӡ" ,
	TOOL_XLS : "���� xls" ,
	TOOL_PDF : "���� pdf" ,
	TOOL_CSV : "���� csv" ,
	TOOL_XML : "���� xml",
	TOOL_FILTER : "����" ,
	TOOL_CHART : "ͼ��"  

};

GT.Msg.Grid['default']=GT.Msg.Grid.cn;


if (!GT.Msg.Validator){
	GT.Msg.Validator={ };
}

GT.Msg.Validator.cn={

		'required'	: '{0#����}�Ǳ�����Ŀ��',
		'date'		: '{0#����}��������ȷ������({1#YYYY-MM-DD})��',
		'time'		: '{0#����}��������ȷʱ��({1#HH:mm})��',
		'datetime'	: '{0#����}��������ȷ�����ں�ʱ��({1#YYYY-MM-DD HH:mm})��',
		'email'		: '{0#����}��������ȷ��email��ʽ��',
		'telephone'	: '{0#����}��������ȷ�ĵ绰���룡',
		'number'	: '{0#����}������������ʽ��',
		'integer'	: '{0#����}������������ʽ��',
		'float'		: '{0#����}������������С����ʽ��',
		'money'		: '{0#����}��������������λС����ʽ��',
		'range'		: '{0#����}�ķ�Χ����Ҫ��{1}��{2}֮�䣡',
		'equals'	: '{0#����}������{1#��һ��}��ȣ�',
		'lessthen'	: '{0#����}���ܴ���{1#��һ��}��',
		'idcard'	: '{0#����}��������ȷ�����֤���룡',

		'enchar'	: '{0#����}��������ͨӢ���ַ�����ĸ�����ֺ��»��ߡ�',
		'cnchar'	: '{0#����}�����������ַ���',
		'minlength'	: '{0#����}�ĳ��Ȳ���С��{1}���ַ���',
		'maxlength'	: '{0#����}�ĳ��Ȳ��ܴ���{1}���ַ���'

}

GT.Msg.Validator['default'] = GT.Msg.Validator.cn;

//