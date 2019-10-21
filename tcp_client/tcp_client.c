#include <stdio.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <string.h>

int main(int argc, char *argv[]) {

  if (argc != 4) {
    printf("以下の値を指定して実行してください。\n\n第一引数:送信先IPアドレス\n第二引数:送信先ポート番号\n第三引数:送信メッセージ\n");
    return 1;
  }

  char *ipAddress = argv[1];
  char *portNo = argv[2];
  char *message = argv[3];

  struct sockaddr_in server;
  int socket0 = socket(AF_INET, SOCK_STREAM, 0);

  server.sin_family = AF_INET;
  server.sin_port = htons(atoi(portNo));
  server.sin_addr.s_addr = inet_addr(ipAddress);

  connect(socket0, (struct sockaddr *)&server, sizeof(server));

  if(send(socket0, message, strlen(message), 0) < 0) {
      perror("send");
      return -1;
  }

  printf("send to %s:%s %s\n", ipAddress, portNo, message);

  close(socket0);

  return 0;
}
