# Logger
Keylogger em Java – Projeto de Estudo
Sobre o Projeto

Este é um projeto de estudo desenvolvido em Java com o objetivo de aprender e praticar a integração de programação com conceitos de redes e segurança cibernética.

Como iniciante na área de cibersegurança, decidi juntar meus conhecimentos de rede com programação, explorando conceitos como captura de eventos de teclado, manipulação de arquivos e envio de informações via SMTP.

⚠️ Este projeto é apenas para fins educativos e de estudo em ambiente controlado. Não deve ser utilizado para fins maliciosos ou em computadores de terceiros sem autorização.

Funcionalidades

Captura de Teclas

Captura todas as teclas digitadas no sistema usando a biblioteca JNativeHook.

Diferencia teclas comuns (letras e números) de teclas especiais (Enter, Tab, Esc, Backspace, Ctrl, Alt).

Registro em Arquivo

Armazena todas as teclas capturadas em um arquivo de log localizado na pasta Downloads do usuário (keylogger.log).

Envio de Log por Email

Envia automaticamente o arquivo de log para um email configurado usando SMTP do Gmail.

Pode ser acionado de duas formas:

Ao encerrar o programa (shutdown hook).

Em horário pré-configurado, verificado a cada minuto (ScheduledExecutorService).

Estrutura do Código
1. Main.java

Classe principal que registra e captura as teclas.

Agenda o envio do log periodicamente.

Exemplo de shutdown hook para envio automático ao encerrar o programa.

2. SendMailLogger.java

Classe responsável por enviar o arquivo de log por email.

Utiliza JavaMail para conexão SMTP com autenticação e envio de arquivos anexados.

3. SendDate.java

Classe que verifica o horário do sistema.

Chama SendMailLogger quando a hora configurada é atingida.

Tecnologias e Bibliotecas

Java – Linguagem principal.

JNativeHook – Captura global de teclado.

JavaMail (javax.mail) – Envio de emails via SMTP.

Executors & ScheduledExecutorService – Agendamento periódico de tarefas.

NIO (Paths) – Manipulação de caminhos de arquivos.

Observações de Estudo

Uso de try-with-resources para evitar vazamentos de arquivo.

Separação de responsabilidades entre classes (captura de teclado, envio de email e verificação de horário).

Experiência prática integrando programação com conceitos de rede e automação de tarefas.

Senhas de email devem ser senhas de aplicativo, nunca a senha principal da conta.

Todo o código foi desenvolvido para fins educativos e de prática controlada.

Conclusão

Este projeto marca o início da minha jornada na área de cibersegurança, combinando programação e conhecimento de rede. O foco é entender como eventos do sistema podem ser capturados, registrados e processados de forma automatizada.
