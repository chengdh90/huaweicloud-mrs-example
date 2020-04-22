package com.huawei.bigdata.zookeeper.examples.callback;

import com.huawei.bigdata.zookeeper.examples.handler.ZookeeperCallbackHandlerExample;
import org.apache.log4j.Logger;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.KeeperException.Code;

/**
 * This callback is used to retrieve the name of the node.
 */
public class ZookeeperAsyncStringCallbackExample implements
        AsyncCallback.StringCallback
{
    private static final Logger LOG = Logger.getLogger(ZookeeperAsyncStringCallbackExample.class.getName());

    /**
     * Process the result of the asynchronous call.
     * <p/>
     * On success, rc is {@link Code#OK}.
     * <p/>
     * On failure, rc is set to the corresponding failure code in
     * {@link KeeperException}.
     * <ul>
     * <li>
     * {@link Code#NODEEXISTS} - The node
     * on give path already exists for some API calls.</li>
     * <li>
     * {@link Code#NONODE} - The node on
     * given path doesn't exist for some API calls.</li>
     * <li>
     * {@link Code#NOCHILDRENFOREPHEMERALS}
     * - an ephemeral node cannot have children. There is discussion in
     * community. It might be changed in the future.</li>
     * </ul>
     *
     * @param rc The return code or the result of the call.
     * @param path The path that we passed to asynchronous calls.
     * @param ctx Whatever context object that we passed to asynchronous calls.
     * @param name The name of the Znode that was created. On success,
     *        <i>name</i> and <i>path</i> are usually equal, unless a sequential
     *        node has been created.
     */
    @Override
    public void processResult(int rc, String path, Object ctx, String message)
    {
        try
        {
            if (ctx.getClass()
                    .equals(Class
                            .forName("com.huawei.bigdata.zookeeper.examples.handler.ZookeeperCallbackHandlerExample")))
            {
                ZookeeperCallbackHandlerExample handler = (ZookeeperCallbackHandlerExample) ctx;
                if (rc == Code.Ok)
                {
                    handler.handle("reveive async message: " + path + " : "
                            + message);
                }
                else
                {
                    Code code = Code.get(rc);
                    handler.handle("reveive async message: "
                            + KeeperException.create(code).getMessage());
                }
            }
            else
            {
                LOG.info("Error occured when handle asynchronous string calls");
            }
        }
        catch (ClassNotFoundException e)
        {
            LOG.info("Error occured when handle asynchronous string calls");
        }

    }
}
