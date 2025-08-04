package org.example;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.io.File;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;

public class Main {

    static {
        System.setProperty("org.lwjgl.librarypath", new File("src/main/resources/natives").getAbsolutePath());
    }

    public static void main(String[] args) throws LWJGLException {
        // 1. Настройка окна
        Display.setDisplayMode(new DisplayMode(800, 600));
        Display.setTitle("LWJGL 2.9.3 — 3D Cube");
        Display.create();

        // 2. Настройка 3D-проекции
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        gluPerspective(45.0f, 800f/600f, 0.1f, 100f);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_DEPTH_TEST);

        // 3. Главный цикл
        while (!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glLoadIdentity();

            // Камера (отодвигаемся от куба)
            glTranslatef(0, 0, -5);

            // Вращение куба
            glRotatef(System.currentTimeMillis() % 360, 0.5f, 1, 0);

            // Рисуем куб
            glBegin(GL_QUADS);
            // Передняя грань (красная)
            glColor3f(1, 0, 0);
            glVertex3f(-1, -1, 1); glVertex3f(1, -1, 1);
            glVertex3f(1, 1, 1); glVertex3f(-1, 1, 1);

            // Задняя грань (зелёная)
            glColor3f(0, 1, 0);
            glVertex3f(-1, -1, -1); glVertex3f(1, -1, -1);
            glVertex3f(1, 1, -1); glVertex3f(-1, 1, -1);

            // Остальные 4 грани (синие, жёлтые...)
            // ...
            glEnd();

            Display.update();
        }

        Display.destroy();
    }
}