package com.zhushen.bluetoothcmdsender.command.sender;

/**
 * Created by Zhushen on 2018/3/14.
 * 按压/吹气错误提示：
 * 第二字节：0：无提示；2：按压过大；3：按压不足
 * 4：按压位置错误；5：按压次数太少；6：释放不足
 * 7：按压次数太多
 * 8：按压频率不正确；
 * 9：吹气过大；10：吹气不足；11：进胃
 * 12：气道堵塞；13：吹气次数太少；14：吹气次数太多
 */

public class ErrorSender extends AbsCommonCmdSender {

    public ErrorSender(String[] data) {
        super(data);
    }

    public static final ErrorSender PRESS_OVER = new ErrorSender(new String[] {"02"});
    public static final ErrorSender PRESS_LESS = new ErrorSender(new String[] {"03"});
    public static final ErrorSender PRESS_POSITION = new ErrorSender(new String[] {"04"});
    public static final ErrorSender PRESS_COUNT_LESS = new ErrorSender(new String[] {"05"});
    public static final ErrorSender PRESS_RELEASE = new ErrorSender(new String[] {"06"});
    public static final ErrorSender PRESS_COUNT_OVER = new ErrorSender(new String[] {"07"});
    public static final ErrorSender PRESS_FREQUENCY_ERROR = new ErrorSender(new String[] {"08"});
    public static final ErrorSender INFLATE_OVER = new ErrorSender(new String[] {"09"});
    public static final ErrorSender INFLATE_LESS = new ErrorSender(new String[] {"10"});
    public static final ErrorSender INFLATE_STOMACH = new ErrorSender(new String[] {"11"});
    public static final ErrorSender AIR_WAY = new ErrorSender(new String[] {"12"});
    public static final ErrorSender INFLATE_COUNT_LESS = new ErrorSender(new String[] {"13"});
    public static final ErrorSender INFLATE_COUNT_MORE = new ErrorSender(new String[] {"14"});

    @Override
    protected String indicateHeader() {
        return "A2";
    }

    @Override
    protected String[] indicateData() {
        return data;
    }
}
