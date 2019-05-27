const QuestionTypes = {
    BASE_TYPE_SINGLE_CHOICE     : 1, // 单选题单选题
    BASE_TYPE_MULTI_CHOICE      : 2, // 多选题多选题
    BASE_TYPE_TFNG              : 3, // 判断题判断题: true(是), false(否), not given(未提及)
    BASE_TYPE_ESSAY_QUESTION    : 4, // 问答题问答题
    BASE_TYPE_FILL_IN_THE_BLANK : 5, // 填空题填空题
    BASE_TYPE_COMPREHENSION     : 6, // 复合题
    BASE_TYPE_HOMEWORK          : 7, // 作业题
    BASE_TYPE_DESCRIPTION       : 8, // 描述
};

// 教学阶段
const PHASES = ['高中', '初中', '小学'];

// 学科
const SUBJECTS = ['语文', '数学', '英语', '物理', '化学', '生物', '地理', '政治', '历史'];

// 省
const PROVINCES = ['北京', '上海', '天津', '重庆', '河北', '辽宁', '黑龙江', '吉林', '山东', '山西', '安徽', '浙江',
    '江苏', '江西', '广东', '福建', '海南', '河南', '湖北', '湖南', '四川', '云南', '贵州', '陕西', '甘肃',
    '青海', '内蒙古', '广西', '西藏', '新疆', '香港', '澳门', '台湾',
];

// 课程管理类别名称
const CategoryName = [
    { label: '小学', value: 1 },
    { label: '初中', value: 2 },
    { label: '高中', value: 3 },
    { label: '大学', value: 4 }
];
// 视频管理——存储位置
const StorageLocation = [
    { label: '阿里云', value: 1 },
    { label: 'ftp',   value: 2 }
];
// 视频管理——视频来源
const VideoSources = [
    { label: '华夏大地', value: 1 },
    { label: '外部课件', value: 2 },
];

// 调用类别
const CallCategories = [
    { label: '自学考试', value: 1 },
    { label: '公共英语', value: 2 },
    { label: '英语四六级', value: 3 },
    { label: '教师资格', value: 4 },
    { label: '成教课程', value: 5 },
    { label: '消防工程', value: 6 },
];

// 试题类型
const QuesstionType = [
    { label: '单选题',  value: 1 },
    { label: '多选题',  value: 2 },
    // { label: '判断题',  value: 3 },
];

// 是否有效
const IsEnabled = [
    { value: '1', label: '是' },
    { value: '0', label: '否' },
];

// 字典类型
const DictTypes = [
    { value: '课件类型', label: '课件类型' },
    { value: '课件来源', label: '课件来源' },
    { value: '视频来源', label: '视频来源' },
    { value: '视频清晰度', label: '视频清晰度' },
    { value: '服务器类型', label: '服务器类型' },
];

// 清晰度
const ClarityLevels = [
    { value: '1', label: '标清' },
    { value: '2', label: '高清' },
    { value: '3', label: '普清' },
];

// 课件来源
const CoursewareSource = [
    { value: 1, label: '华夏总公司' },
    { value: 2, label: '湖南分公司' },
    { value: 3, label: '湖北分公司' },
];

// 讲义类型
const LectureTypes = [
    { value: 1, label: '精讲' },
    { value: 2, label: '串讲' },
];
