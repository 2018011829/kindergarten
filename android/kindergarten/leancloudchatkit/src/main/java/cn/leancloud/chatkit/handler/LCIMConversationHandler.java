package cn.leancloud.chatkit.handler;

import android.widget.Toast;

import cn.leancloud.AVOSCloud;
import cn.leancloud.im.v2.AVIMClient;
import cn.leancloud.im.v2.AVIMMessage;
import cn.leancloud.im.v2.AVIMConversation;
import cn.leancloud.im.v2.AVIMConversationEventHandler;

import java.util.List;

import cn.leancloud.chatkit.cache.LCIMConversationItemCache;
import cn.leancloud.chatkit.event.LCIMConversationReadStatusEvent;
import cn.leancloud.chatkit.event.LCIMOfflineMessageCountChangeEvent;
import cn.leancloud.chatkit.event.LCIMMessageUpdatedEvent;
import cn.leancloud.chatkit.utils.LCIMLogUtils;
import cn.leancloud.im.v2.AVIMMessageManager;
import de.greenrobot.event.EventBus;

/**
 * Created by wli on 15/12/1.
 * 和 Conversation 相关的事件的 handler
 * 需要应用主动调用  AVIMMessageManager.setConversationEventHandler
 * 关于回调会何时执行可以参见 https://leancloud.cn/docs/realtime_guide-android.html#添加其他成员
 */
public class LCIMConversationHandler extends AVIMConversationEventHandler {

  private static LCIMConversationHandler eventHandler;

  public static synchronized LCIMConversationHandler getInstance() {
    if (null == eventHandler) {
      eventHandler = new LCIMConversationHandler();
    }
    return eventHandler;
  }

  private LCIMConversationHandler() {
  }

  @Override
  public void onUnreadMessagesCountUpdated(AVIMClient client, AVIMConversation conversation) {
    LCIMConversationItemCache.getInstance().insertConversation(conversation.getConversationId());
    AVIMMessage lastMessage = conversation.getLastMessage();
    System.out.println("LCIMConversationHandler#onUnreadMessagesCountUpdated conv=" + conversation.getConversationId() + ", lastMsg: " + lastMessage.getContent());
    EventBus.getDefault().post(new LCIMOfflineMessageCountChangeEvent(conversation, lastMessage));
  }

  @Override
  public void onLastDeliveredAtUpdated(AVIMClient client, AVIMConversation conversation) {
    LCIMConversationReadStatusEvent event = new LCIMConversationReadStatusEvent();
    event.conversationId = conversation.getConversationId();
    EventBus.getDefault().post(event);
  }

  @Override
  public void onLastReadAtUpdated(AVIMClient client, AVIMConversation conversation) {
    LCIMConversationReadStatusEvent event = new LCIMConversationReadStatusEvent();
    event.conversationId = conversation.getConversationId();
    EventBus.getDefault().post(event);
  }

  /**
   * 实现本方法以处理聊天对话中的参与者离开事件
   *
   * @param client
   * @param conversation
   * @param members 离开的参与者
   * @param kickedBy 离开事件的发动者，有可能是离开的参与者本身
   * @since 3.0
   */
  @Override
  public void onMemberLeft(AVIMClient client, AVIMConversation conversation, List<String> members, String kickedBy) {
    // 因为不同用户需求不同，此处暂不做默认处理，如有需要，用户可以通过自定义 Handler 实现
    Toast.makeText(AVOSCloud.getContext(),
            members + " 离开对话 " + conversation.getConversationId() + "；操作者为："
                    + kickedBy, Toast.LENGTH_SHORT).show();
  }

  /**
   * 实现本方法以处理聊天对话中的参与者加入事件
   *
   * @param client
   * @param conversation
   * @param members 加入的参与者
   * @param invitedBy 加入事件的邀请人，有可能是加入的参与者本身
   * @since 3.0
   */
  @Override
  public void onMemberJoined(AVIMClient client, AVIMConversation conversation, List<String> members, String invitedBy) {
      // 手机屏幕上会显示一小段文字：Mary 加入到 551260efe4b01608686c3e0f；操作者为：Tom
      Toast.makeText(AVOSCloud.getContext(),
              members + " 加入到 " + conversation.getConversationId() + "；操作者为："
                      + invitedBy, Toast.LENGTH_SHORT).show();

  }


  /**
   * 实现本方法来处理当前用户被踢出某个聊天对话事件
   *
   * @param client
   * @param conversation
   * @param kickedBy 踢出你的人
   * @since 3.0
   */
  @Override
  public void onKicked(AVIMClient client, AVIMConversation conversation, String kickedBy) {
    Toast.makeText(AVOSCloud.getContext(),
            "你已离开对话 " + conversation.getConversationId() + "；操作者为："
                    + kickedBy, Toast.LENGTH_SHORT).show();
  }


  @Override
  public void onInvited(AVIMClient client, AVIMConversation conversation, String operator) {

  }

  @Override
  public void onMessageRecalled(AVIMClient client, AVIMConversation conversation, AVIMMessage message) {
    EventBus.getDefault().post(new LCIMMessageUpdatedEvent(message));
  }

  @Override
  public void onMessageUpdated(AVIMClient client, AVIMConversation conversation, AVIMMessage message) {
    LCIMLogUtils.d("message " + message.getMessageId() + " updated!");
    EventBus.getDefault().post(new LCIMMessageUpdatedEvent(message));
  }
}

