const Urls = {
    // 用户
    API_USERS_BY_ID          : '/api/users/{userId}',           // 指定 ID 的用户
    API_USER_NICKNAMES       : '/api/users/{userId}/nicknames', // 用户的昵称
    API_USER_AVATARS         : '/api/users/{userId}/avatars',   // 用户的头像 URL
    API_USER_GENDERS         : '/api/users/{userId}/genders',   // 用户性别
    API_USER_MOBILES         : '/api/users/{userId}/mobiles',   // 用户手机
    API_USER_PASSWORDS       : '/api/users/{userId}/passwords', // 用户密码
    API_USER_PASSWORDS_RESET : '/api/users/{userId}/passwords/reset', // 重置密码
    API_LOGIN_USERS_CURRENT  : '/api/login/users/current',      // 当前登录的用户

    FORM_UPLOAD_TEMPORARY_FILE : '/form/upload/temp/file',  // 上传一个临时文件
    FORM_UPLOAD_TEMPORARY_FILES: '/form/upload/temp/files', // 上传多个临时文件
    API_CAN_PREVIEW_FILE_PREFIX: '/api/canPreview',         // 请求是否可预览文件的前缀

    // 消息系统
    MESSAGE_WEBSOCKET_URL : `ws://${window.location.hostname}:3721`,

    // 教材管理
    API_TEXTBOOK : '/api/textbooks',
    API_TEXTBOOKS_ID : '/api/textbooks/{textbookId}', // 删除教材
    API_VIEWTEXTBOOKS_ID :'/api/courses/textBook/{id}', //查看教材

    //类别管理
    API_BASELINE : '/api/baselines',
    API_BASELINES_ID : '/api/baselines/{baseLineId}/{code}',

    //课件类型
    API_COURSEWARETYPE : '/api/types',

    //教师管理
    API_TEACHER : '/api/teachers',
    API_TEACHERS_ID : '/api/teachers/{id}', // 删除教师

    //试题管理
    API_QUESTION : '/api/questions',
    API_QUESTIONS_ID : '/api/questions/{questionId}', // 删除试题
    API_COURSEWARE_QUESTIONS    : '/api/courseware/questions',   // 查询课件关联试题列表

    //服务器管理
    API_SERVER : '/api/servers',
    API_SERVERS_ID : '/api/servers/{serverId}',

    // 视频
    API_MEDIA_ID : '/api/media/{mediaId}',          // 单个查询/修改/删除
    API_MEDIA : '/api/media',                       // 插入/查询列表
    API_MEDIA_TYPE: '/api/media/durationAndPlayAuth',   //判断视频是否为阿里云，系统获取视频时长
    API_MEDIA_SERVERTYPE: '/api/servers/code/{code}',   //判断视频是否为阿里云，系统获取视频时长

    // 知识点
    API_KNOWLEDGE_POINTS : '/api/knowledgePoints',               // 根据条件查询知识点集合
    API_KNOWLEDGE_POINTS_ID : '/api/knowledgePoints/{pointId}',  // 删除知识点

    // 字典
    API_DICT_ID : '/api/dict/{dictId}',      // 单个查询/修改/删除
    API_DICT : '/api/dict',                  // 插入/查询列表
    API_DICT_TYPES : '/api/dict/types',      // 获取字典类型列表

    //课程管理
    API_QUERY :'/api/courses',  //课程查询
    API_COURSE_ID : '/api/courses/{id}', //删除课程/修改课程

    // 课件
    API_COURSEWARES_INFOS : '/api/coursewares',   // 查询、创建课件
    API_COURSEWARES_DELETE_OR_UPDATE : '/api/coursewares/{id}', // 删除、修改课件
    API_COURSEWARES_COPY_CW : '/api/coursewares/{code}/copyCourseware', // 课件表、课件与教材、教师关联表的复制
    API_COURSEWARES_UPDATECWSTATUS   : '/api/coursewares/{id}/updateCwStatus',  // 课件启用、禁用
    API_COURSEWARES_FINDCWBYCODE     : '/api/coursewares/{code}/findCwByCode',  // 根据课件 CODE 查询单条记录
    API_COURSEWARES_EXPORT_EXCEL     : '/api/coursewares/exportExcel',          // 导出课件

    //调用管理
    API_APPLICATION :'/api/client',  //应用查询/新增
    API_APPLICATION_ID:'/api/client/{id}',  //删除/修改应用
    API_APPLICATION_RESET_ID:'/api/clientReset/{id}',   //重置秘钥
    API_APPLICATION_COURSEWARE_ID: '/api/client/courseware/{id}',  //根据应用id查询相关课件
    API_APPLICATION_LOG:'/api/clientlog',   //应用日志
    API_SELECTED_COURSEWARES:'/api/client/{clientCode}/{businessLineCode}',    //查看已经选择的课件
    API_CLIENTS_BUSINESS_lINE_COURSE_COURSEWARE : '/api/client/businesslineCourseCourseware',             // 获取所有业务线及下属的课程
    API_CLIENTS_BUSINESS_lINE_COURSE_COURSEWARE_ID : '/api/client/{code}/businesslineCourseCourseware',   // 获取指定应用客户端下的所有业务线及下属的课程、课件

    // 章节目录
    API_CATALOGS : '/api/catalogs',                  // 查询/新增/更新章节目录
    API_CATALOGS_MEDIA : '/api/catalog/media',       // 绑定章节和媒体关系
    API_CATALOGS_ID : '/api/catalogs/{catalogId}',   // 删除章节目录
    API_CATALOG_DRAG : '/api/catalogs/drag',         // 拖拽章节目录

    // 讲义
    API_LECTURES : '/api/lectures',    // 讲义新增/讲义修改/讲义关联课件查询
    API_COURSEWARE_LECTURES : '/api/courseware/lectures',     // 查询课件章节关联讲义列表
    API_LECTURES_ID : '/api/catalogs/{catalogId}/lectures/{lectureId}',    // 讲义删除

    // 媒体知识点
    API_MEDIA_KNOWLEDGEPOINTS : '/api/catalogknowledgepoints',           // 根据条件插入
    API_MEDIA_KNOWLEDGEPOINTS_ID : '/api/catalogknowledgepoints/{catalogKnowledgePointId}',   // 删除媒体知识点
    API_MEDIA_KNOWLEDGEPOINTS_MEDIAID : '/api/mediaknowledgepoints/{mediaId}',    // 根据章节 ID 查询知识点

    // 课件统计
    API_COURSEWARESTATISTICS: '/api/coursewareStatistics', //课件统计、课程统计

    // 根据课件编码和章节 ID 及媒体 ID
    API_COURSEWARE_CATALOG_MEDIA : '/api/findinfo/coursewares/{catalogId}', // 获取媒体所有讲义/知识点/弹题信息

    // 通过媒体编码及服务器编码获取媒体时长及认证信息
    API_MEDIA_PLAYAUTH : '/api/media/durationAndPlayAuth'
};
