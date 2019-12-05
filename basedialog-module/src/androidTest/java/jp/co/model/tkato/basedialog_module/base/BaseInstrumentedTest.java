package jp.co.model.tkato.basedialog_module.base;

import java.util.concurrent.CountDownLatch;

@SuppressWarnings({"WeakerAccess", "unused"})
public class BaseInstrumentedTest {

    private CountDownLatch latch;

    public CountDownLatch getLatch() {
        return latch;
    }

    @SuppressWarnings("UnusedReturnValue")
    public CountDownLatch countSetup(int count) {
        latch = new CountDownLatch(count);
        return latch;
    }

    @SuppressWarnings("all")
    public void tearDown() throws Exception {
        countFinish();
        latch = null;
    }

    protected final void countFinish() {
        if (null == latch) {
            return;
        }
        final int len = (int) latch.getCount();
        for (int i = 0; i < len; i++) {
            latch.countDown();
        }
    }
}
