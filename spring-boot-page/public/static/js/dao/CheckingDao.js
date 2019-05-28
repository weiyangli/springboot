export default class CheckingDao {
    /**
     * 查询字典
     *
     * @param   value   字典名称
     * @param   type    字典类型
     * @param  {Function} callback 请求成功的回调函数
     * @return 无返回值
     */
    static findChecking(callback) {
        $.rest.syncGet({ url: Urls.API_CHECKING, success: (result) => {
            if (result.success) {
                console.log(result);
                const checking = result.data;
                callback(checking);
            } else {
                Utils.warning('查询考勤错误', result.message);
            }
        }
        });
    }
}
