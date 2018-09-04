package com.mm.blog.response;

/**
 * @Auther: mm
 * @Date: 2018/8/27 16:19
 * @Description:
 */
public enum ResponseStatus {


    /**
     * 参数异常
     */
    FAIL_PARAMETER_EXCEPTION("1000400", "参数异常"),

    /**
     * 用户名或密码错误
     */
    EMAIL_OR_PASSWORD_ERROR("1000401", "用户名或密码错误"),

    /**
     * 登录成功
     */
    LOGIN_SUCCESS("1000200", "登录成功"),

    /**
     * 注册成功
     */
    REGISTER_SUCCESS("1000201", "register success"),

    /**
     * 两次密码输入不一致
     */
    PASSWORD_NOT_SAME_ERROR("1000402", "两次密码输入不一致"),
    /**
     * 保存文章失败
     */
    SAVE_ARTICLE_ERROR("1000403", "保存文章失败"),
    /**
     * 查询用户成功
     */
    FIND_USER_SUCCESS("1000202", "查询用户成功"),

    /**
     * 修改用户成功
     */
    UPDATE_USER_SUCCESS("1000203", "修改用户成功"),

    /**
     * tag新增成功
     */
    SAVE_TAG_SUCCESS("1000204", "tag新增成功"),

    /**
     * 查询标签成功
     */
    FIND_TAGS_SUCCESS("1000205", "查询标签成功"),
    /**
     * 更新标签成功
     */
    UPDATE_TAG_SUCCESS("1000206", "更新标签成功"),

    /**
     * 保存文章成功
     */
    SAVE_ARTICLE_SUCCESS("1000207", "保存文章成功"),

    /**
     * 查询文章成功
     */
    FIND_ARTICLE_SUCCESS("1000208", "查询文章成功"),
    /**
     * 文章修改成功
     */
    UPDATE_ARTICLE_SUCCESS("1000209", "文章修改成功"),
    ;

    /**
     * response code
     */
    private String code;

    /**
     * response message
     */
    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ResponseStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
