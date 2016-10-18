/*
 * Copyright (C) Cristian Sulea ( http://cristian.sulea.net )
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package jatoo.apps.telegram;

import org.telegram.telegrambots.bots.commands.BotCommand;

import jatoo.apps.telegram.commands.JaTooTelegramBotWeatherCommand;
import jatoo.telegram.JaTooTelegramBot;
import jatoo.telegram.JaTooTelegramUtils;

public class JaTooTelegramWeatherBot extends JaTooTelegramBot {

  public static final String USERNAME = "JaTooWeatherBot";
  public static final String TOKEN = "250995795:AAG15xz4yvOpAmqA1ypG6tjvLAh0snhomi0";

  public JaTooTelegramWeatherBot(BotCommand... commands) {
    super(USERNAME, TOKEN, commands);
  }

  public static void main(String[] args) throws Throwable {
    JaTooTelegramUtils.registerBot(new JaTooTelegramWeatherBot(new JaTooTelegramBotWeatherCommand()));
  }

}
