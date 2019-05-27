/**
 * 题目的工具类
 *
 * 提示:
 * 1. 基本题型: 单选题(1)、多选题(2)、判断题(3)、问答题(4)、填空题(5)、复合题(6)、作业题(7)、描述(8)
 * 2. newAdded 的特殊用途:
 *    新创建的题目和选项提交到服务器时 ID 都必须为 0，这是因为服务器在处理时发现 ID 为 0 表明是新建的，
 *    非 0 则说明是已经存在的，会为它们生成一个全局唯一的 ID。
 *    但是在 appendQuestionOption() 中生成选项时又给定了一个非 0 ID，这是因为 Vue 的 v-for 中遍历选项时
 *    使用 :key 绑定 ID，那么 ID 就不能为 0 了，所以就给新创建的题目和选项增加了一个标记 newAdded 为 true，
 *    在提交时发现 newAdded 为 true 就把它们的 ID 设置为 0。
 * 3. deleted 的用途:
 *    页面上删除题目或者选项时不时把它们从对应的数组里删除，而是设置他们的 deleted 为 true，界面上不显示它们，提交时:
 *    2.1 如果 newAdded 为 true  (新创建的)，deleted 为 true，那么先把他们从数组里删除掉，不传给服务器，因为 ID 为 0，服务器知道是新创建的
 *    2.2 如果 newAdded 为 false (已存在的)，deleted 为 true，要传给服务器，服务器处理时从数据库里删除它们，ID 不为 0，服务器知道是已存在的
 * 4. 添加、删除题目的选项后，需要调用 updateOptionMarks() 更新一下选项的标记 A, B, C, D
 * 5. 在提交到服务器的时候，需要调用 cleanQuestion() 清理一下题目，删除其中 newAdded 和 deleted 同时为 true 的项
 */
export default class QuestionUtils {
    /**
     * 创建题目
     *
     * @param  {Integer} id       题目的 ID
     * @param  {Integer} questionType 题目的类型
     * @param  {String}  stem     题干
     * @param  {Integer} purpose  用途
     * @return {JSON} 返回新建题目的 JSON 对象
     */
    static createQuestion(id, questionType, stem = '') {
        // 1. 创建题目对象
        // 2. 如果是单选题或者多选题，则增加 4 个默认选项，并且设置第 0 个选项为正确答案
        // 3. 如果是判断题，则增加 2 个默认选项，并且设置第 1 个选项为正确答案
        // 4. 如果是问答题、作业题，则创建 1 个选项，用于存储问答题的答案
        // 5. 更新选项的标记
        if (!stem) {
            switch (questionType) {
            case 1: stem = '<p>单选题</p>'; break;
            case 2: stem = '<p>多选题</p>'; break;
            case 3: stem = '<p>判断题</p>'; break;
            case 4: stem = '<p>问答题</p>'; break;
            case 5: stem = '<p>填空题</p>'; break;
            case 6: stem = '<p>复合题</p>'; break;
            default:
            }
        }

        // [1] 创建题目对象
        let question= {
            questionId    : 0,
            mediaTime     : 0,   // 弹题时间
            readyMediaTime :'',  // 弹题编辑模式时间
            mediaId       : 0,   // 媒体Id
            mediaTitle    : '',  // 媒体名称
            coursewares   : '',  // 所属课件
            coursewareCode: '',  // 课件编码
            catalogId     : 0,   // 章节Id
            examinePoint  : '',  // 实体考核点
            questionType  : questionType,  // 基本题型: 单选题(1)、多选题(2)、判断题(3)
            questionStem  : '<p>请输入题干</p>', // 题干
            answer        : '',  // 答案
            analysis      : '',  // 解析
            isEnabled     : 1,   // 是否启用(0无效1有效)
            createdBy     : 0,   // 创建人
            updatedBy     : 0,   // 更新人
            createdAt     : new Date().format('yyyy-MM-dd hh:mm:ss'),
            updatedAt     : new Date().format('yyyy-MM-dd hh:mm:ss'),
            optionList    : [],
        };

        if (1 == questionType || 2 == questionType) {
            // [2] 如果是单选题或者多选题，则增加 4 个默认选项，并且设置第 0 个选项为正确答案
            QuestionUtils.appendQuestionOption(question);
            QuestionUtils.appendQuestionOption(question);
            QuestionUtils.appendQuestionOption(question);
            QuestionUtils.appendQuestionOption(question);
        } else if (3 == questionType) {
            // [3] 如果是判断题，则增加 2 个默认选项，并且设置第 1 个选项为正确答案
            QuestionUtils.appendQuestionOption(question, false, '正确');
            QuestionUtils.appendQuestionOption(question, false, '错误');
        }

        // [5] 更新选项的标记
        QuestionUtils.updateOptionMarks(question);

        return question;
    }

    /**
     * 给题目增加一个新的选项
     *
     * @param  {JSON}    question 题目
     * @param  {Boolean} correct  是否正确答案，默认值为 false
     * @param  {String}  content  选项的内容
     * @return {JSON} 返回新增加的选项
     */
    static appendQuestionOption(question, correct = false, quesOption = '<p>新建选项</p>') {
        const option = {
            id          : Utils.nextId(),
            quesOption,
            correct,
            questionId  : question.questionId,
        };

        question.optionList.push(option);
        QuestionUtils.updateOptionMarks(question);

        return option;
    }

    /**
     * 删除题目指定下标的选项
     * 只是修改选项的 deleted 为 true，标记为被删除，没有真的从题目的选项数组里删除
     *
     * @param  {JSON}    question    题目
     * @param  {Integer} optionIndex 要删除的选项的下标
     * @return 无返回值
     */
    static deleteQuestionOption(question, optionIndex) {
        question.optionList.remove(optionIndex);
        // 更新选项标记
        QuestionUtils.updateOptionMarks(question);
    }

    /**
     * 删除题目的最后一个选项
     *
     * @param  {JSON} question 题目
     * @return 无返回值
     */
    static deleteQuestionLastOption(question) {
        // 找到最后一个 deleted 为 false 的选项，删除它
        for (let i = question.optionList.length - 1; i >= 0; --i) {
            if (!question.optionList[i].deleted) {
                QuestionUtils.deleteQuestionOption(question, i);
                break;
            }
        }
    }

    /**
     * 校验题目是否有效: 题干和选项不能为空，有效返回 true，否则返回 false
     *
     * @param  {JSON} question 题目
     * @return {Boolean} 题目有效时返回 true，否则返回 false
     */
    static validateQuestion(question) {
        // 1. 题干不能为空
        // 2. 弹题时间只能是数字
        // 2. 单选题和多选题的选项不能为空
        // 3. 单选题、多选题、判断题必须有标准答案，否则返回 false

        // [1] 题干不能为空
        const stemText = $(`<p>${question.questionStem}</p>`).text();
        if (!stemText) {
            Utils.warning('错误', '题干不能为空');
            return false;
        }

        // let reg = /^[0-9]*$/;
        // if (question.mediaTime == 0 || !reg.test(question.mediaTime)) {
        //     Utils.warning('错误', '弹题时间只能大于0的数字');
        //     return false;
        // }

        if (1 == question.questionType || 2 == question.questionType) {
            // [2] 单选题和多选题的选项不能为空
            for (let i = 0; i < question.optionList.length; ++i) {
                const optionText = $(`<p>${question.optionList[i].quesOption}</p>`).text();
                if (!optionText) {
                    Utils.warning('错误', '选项不能为空');
                    return false;
                }
            }
        }

        // [3] 单选题、多选题、判断题必须有标准答案，否则返回 false
        if (1 == question.questionType || 2 == question.questionType || 3 == question.questionType) {
            let optionCount = 0;
            for (let option of question.optionList) {
                optionCount++;
            }
            if (1 == question.questionType && optionCount < 2) {
                Utils.warning('错误', '请确保单选题至少2个选项');
                return false;
            } else if (2 == question.questionType && optionCount < 3) {
                Utils.warning('错误', '请确保多选题至少3个选项');
                return false;
            }

            for (let option of question.optionList) {
                if (option.correct) {
                    return true;
                }
            }

            Utils.warning('错误', '请确保单选题、多选题、判断题有标准答案');
            return false;
        }

        return true;
    }
    // /**
    //  * 给题目选项排序
    //  * @param {JSON} question 题目
    //  * @return 无返回值
    //  */
    // static orderBysequenceNum(question) {

    // }

    /**
     * 更新题目选项的标记为 A, B, C, D，例如添加或删除某个选项后就要重新计算一次
     * Mark 指的是选择题选项的标记 A, B, C, D
     *
     * @param  {JSON} question 题目
     * @return 无返回值
     */
    static updateOptionMarks(question) {
        // 1. 更新选项的标记: 把题目选项中 deleted 为 false 的选项按顺序设置编号 A, B, C, D
        // 2. 更新小题选项的标记: 使用递归

        // [1] 更新选项的标记
        let sn = 0;
        question.optionList.forEach((o) => {
            // 把题目选项中选项按顺序设置编号 A, B, C, D
            o.quesValue = String.fromCharCode(65 + sn); // 65 是 A
            sn += 1;
        });
    }

    /**
     * 标记选项为正确的或者错误的
     *
     * @param  {JSON}    question    题目
     * @param  {Integer} optionIndex 要标记的选项的下标
     * @return 无返回值
     */
    static markQuestionOptionCorrection(question, optionIndex) {
        // 1. 如果是单选题或者判断题
        //    1.1 先把所有选项的 correct 设置为 false
        //    1.2 设置 optionIndex 对应的选项的 correct 为 true
        // 2. 如果是多选题，把 optionIndex 对应的选项的 correct 置反: true 变 false，false 变 true

        const optionList  = question.optionList;  // 题目的所有选项
        const questionType = question.questionType; // 题目的基本类型
        if (1 == questionType || 3 == questionType) {
            // [1] 如果是单选题或者判断题
            // [1.1] 先把所有选项的 correct 设置为 false
            optionList.forEach((o) => {
                o.correct = false;
            });

            // [1.2] 设置 optionIndex 对应的选项的 correct 为 true
            optionList[optionIndex].correct = true;
        } else if (2 == questionType) {
            // [2] 如果是多选题，把 optionIndex 对应的选项的 correct 置反: true 变 false，false 变 true
            optionList[optionIndex].correct = !optionList[optionIndex].correct;
        }
    }
}
