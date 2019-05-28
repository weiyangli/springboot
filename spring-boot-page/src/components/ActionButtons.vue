<!-- ActionButtons 组件用于生成表单操作按钮,
使用方法:
        <ActionButtons :pageCount="pageCount" :importExcelUrl:="importExcelUrl"
        :userButtons="userButtons" @addData="addData" @exportData="exportData" @refreshData="refreshData"/>
参数:
        pageCount: 100, // 总页数
        importExcelUrl: '/api/import', // 导入 excel 请求接口路径(该参数为后端 excel 处理路径,使用导入按钮必填)
        userButtons: '1, 2, 3, 4',     // 使用按钮类型 1: 添加按钮 2: 导入 Excel 按钮 3: 导出按钮 4: 刷新按钮
点击事件(使用对应按钮时写,不使用不写):
    添加按钮发射事件 addData
    导出按钮发射事件 exportData
    刷新按钮发射事件 refreshData
 -->
<template>
    <div class="action-buttons">
        <p v-if="dataCount">总共{{ dataCount }}条</p>
        <!-- 添加按钮 -->
        <Button v-if="userButtons.indexOf(1) > -1" type="primary" icon="md-add" @click="addData">添加</Button>
        <!-- 导入 Excel 按钮 -->
        <Upload
            v-if="userButtons.indexOf(2) > -1"
            :format="['xlsx']"
            :on-success="handleUploadSuccess"
            :on-error="handleUploadError"
            :on-format-error="handleUploadFormatError"
            :before-upload="handleBeforeUpload"
            :show-upload-list="false"
            :action= "importExcelUrl"
            accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
            style="float: right;">
            <Button type="primary" icon="ios-cloud-upload-outline">导入</Button>
        </Upload>
        <!-- 导出按钮 -->
        <Button v-if="userButtons.indexOf(3) > -1" type="primary" icon="ios-cloud-upload-outline" @click="exportData">导出</Button>
        <!-- 刷新按钮 -->
        <Button v-if="userButtons.indexOf(4) > -1" type="primary" icon="refresh" @click="refreshData">刷新</Button>
    </div>
</template>

<script>
export default {
    props: {
        dataCount: { type: Number }, // 总页数
        importExcelUrl: { type: String }, // 导入 excel 请求接口路径
        userButtons: { type: String, required: true }, // 使用按钮类型
    },
    data() {
        return {
        };
    },
    created () {
    },
    methods: {
        // 上传前打开进度条，上传处理完毕后记得关闭进度条
        handleBeforeUpload() {
            this.$Loading.start();
            return true;
        },
        // 上传成功: 经过服务器端处理 (result 是服务器端返回的响应)
        handleUploadSuccess(result, file, fileList) {
            this.$Loading.finish();
            console.log(result);
            if (result.success) {
                this.$Notice.success({ title: '导入成功' });
            } else {
                this.$Notice.warning({ title: '导入的 Excel 模板不对' });
                console.warn(result.data);
            }
        },
        // 上传失败: 经过服务器端处理 (error 不是服务器端返回的响应)
        handleUploadError(error, file, fileList) {
            this.$Loading.error();
            this.$Notice.warning({
                title: '导入失败'
            });
        },
        // 不支持的文件格式: 前端判断
        handleUploadFormatError() {
            this.$Loading.error();
            this.$Notice.warning({
                title: '导入失败',
                desc : '文件格式错误，只支持 Excel xlsx'
            });
        },
        // 添加数据
        addData() {
            this.$emit('addData');
        },
        // 导出数据
        exportData() {
            this.$emit('exportData');
        },
        // 刷新数据
        refreshData() {
            this.$emit('refreshData');
        }
    }
};
</script>

<style lang="scss">
.action-buttons {
    display: flex;
    flex-direction: row;
    padding: 12px;
    p {
        line-height: 32px;
        display: flex;
        flex:1;
    }
    button {
        margin-right: 14px;
    }
}
.ivu-loading-bar {
    .ivu-loading-bar-inner {
        background-color: #19be6b;
    }
}
</style>
