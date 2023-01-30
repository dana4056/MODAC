<template>
  <!-- This is an example component -->
  <div :class="$style.chatbox">
  
  
      <!-- headaer -->
      <div :class="$style.chatbox_header">
        <div :class="$style.chatbox_name">GoingChat</div>
        <div :class="$style.icon_content">RA</div>
      </div>
      <!-- end header -->
  
  
  
      <!-- Chatting -->
      <div :class="$style.chatbox_body">  
        <!-- message -->
        <div :class="$style.chatbox_body_size1">
          <div :class="$style.chatbox_body_size2">
          
          <!-- 내 채팅 한칸 -->
            <div :class="$style.mychat_box_size">
              <div
                :class="$style.mychat_box_content"
              >
                형 저 아파서 조퇴 좀 할게요
              </div>
              <img
                src="https://source.unsplash.com/vpOeXr5wmR4/600x600"
                :class="$style.chat_profile"
                alt=""
              />
            </div>
          <!-- 내 채팅 한칸 끝 -->
            
  
          <!-- 상대 채팅 하나 -->
          <div :class="$style.yourchat_box_size">
            <img
              src="https://source.unsplash.com/vpOeXr5wmR4/600x600"
              :class="$style.chat_profile"
              alt=""
            />
            <div
              :class="$style.yourchat_box_content"
            >
              동현아 아프지마
            </div>
          </div> 
          <!-- 상대 채팅 하나 끝-->
  
          <!-- 내 채팅 한칸 -->
          <div :class="$style.mychat_box_size">
              <div
                :class="$style.mychat_box_content"
              >
                형 그래도 가기전에 이거 채팅 소스 드리고 갈게요 https://tailwindcomponents.com/component/chat-messages/landing
              </div>
              <img
                src="https://source.unsplash.com/vpOeXr5wmR4/600x600"
                :class="$style.chat_profile"
                alt=""
              />
            </div>
          <!-- 내 채팅 한칸 끝 -->
  
          </div>
  
          <!-- 채팅 입력 칸 -->
          <div :class="$style.chat_input_padding">
            <input
              :class="$style.chat_input_box"
              type="text"
              placeholder="채팅을 입력하세요."
            />
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  
  </script>
  
  <style lang="css" module>
  @import './ChatList.module.css'
  </style>