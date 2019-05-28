<template>
    <div class="checking-in">
        <div class="checking-in-content">
            <ActionButtons :userButtons="userButtons" importExcelUrl="/api/checking/import" @refreshData="refreshData"/>
            <Table :columns="columns" :data="checking" class="data-table"></Table>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            userButtons: '2',
            checking: [],
            columns: [
                {
                    title: '标题',
                    key: 'name'
                },
                {
                    title: '日期',
                    key: 'createdAt'
                },
                {
                    title: '操作',
                    render: (h, params) => {
                        return (
                            <div class="table-button">
                                <i-button type='primary' size="small" style="marginRight: 12px;" onClick={() => { this.downloadChecking(params.index); }} >下 载</i-button>
                            </div>
                        );
                    }
                },
            ],
        };
    },
    created () {
        // 查询考勤列表
        this.findChecking();
    },
    methods: {
        // 查询考勤列表
        findChecking() {
            this.checking = [];
            this.$CheckingDao.findChecking((checking) => {
                if (checking.length > 0) {
                    this.checking.push(...checking);
                }
            });
        },
        // 下载考勤记录
        downloadChecking(index) {
            let link = document.createElement('a');
            link.setAttribute('download', '考勤记录.xlsx');
            link.href = this.checking[index].url;
            link.click();
        },
        refreshData() {
            this.findChecking();
        },
    }
};
</script>

<style lang="scss">
.checking-in {
    padding:30px 50px;
}
</style>
