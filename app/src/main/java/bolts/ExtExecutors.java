
package bolts;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public final class ExtExecutors {

//    public static final ExecutorService NETWORK_THREAD = AndroidExecutors.newCachedThreadPool();
//
//    public static final ExecutorService DISK_THREAD = AndroidExecutors.newCachedThreadPool();

    public static final Executor BACKGROUND_THREAD = BoltsExecutors.background();

    public static final Executor UI_THREAD = AndroidExecutors.uiThread();
}
