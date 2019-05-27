<template>
    <div class="login-box">
        <Form ref="formInline" :model="formInline" :rules="ruleInline" class="login-form">
            <h1>管理系统</h1>
            <FormItem prop="username">
                <Input type="text" v-model="formInline.username" placeholder="用户名">
                    <Icon type="ios-person-outline" size="30" slot="prepend"/>
                </Input>
            </FormItem>
            <FormItem prop="password">
                <Input type="password" v-model="formInline.password" placeholder="密码">
                    <Icon type="ios-lock-outline" size="30" slot="prepend" />
                </Input>
            </FormItem>
            <Button type="primary" @click="login()">登 录</Button>
        </Form>
    </div>
</template>

<script>
export default {
    data() {
        return {
            message: 'hello vue',
            formInline: {
                username: '',
                password: ''
            },
            ruleInline: {
                username: [
                    { required: true, message: '请输入用户名', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' },
                    { type: 'string', min: 6, message: '密码最少是6位数', trigger: 'blur' }
                ]
            }
        };
    },
    created() {
    },
    methods: {
        login() {
            alert(1);
        }
    }
};
</script>

<style lang="scss">
body, html, #app {
    width: 100%;
    height: 100%;
    padding: 0;
    margin: 0;
}
.login-box {
    width: 100%;
    height: 100vh;
    background: url("../../../public/static/img/login/bg_all.jpg") no-repeat;
    .login-form {
        background: url("../../../public/static/img/login/b.png") no-repeat;
        position: absolute;
        left: 30%;
        top: 15%;
        width: 600px;
        height: 500px;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        .ivu-form-item {
            .ivu-form-item-content {
                width: 350px;
                input {
                    height: 50px;
                }
            }
        }
        h1 {
            position: absolute;
            top: 70px;
        }
        button {
            width: 350px;
            font-size: 15px;
        }
    }
}
</style>
