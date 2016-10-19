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

package jatoo.apps.telegram.commands;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commands.BotCommand;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import jatoo.apps.telegram.JaTooTelegramWeatherBot;
import jatoo.weather.JaTooWeather;
import jatoo.weather.JaTooWeatherService;
import jatoo.weather.openweathermap.JaTooWeatherOpenWeatherMap;

/**
 * The <code>/weather</code> command for the {@link JaTooTelegramWeatherBot}.
 * 
 * @author <a href="http://cristian.sulea.net" rel="author">Cristian Sulea</a>
 * @version 1.0, October 18, 2016
 */
public class JaTooTelegramBotWeatherCommand extends BotCommand {

  /** The logger. */
  private static final Log LOGGER = LogFactory.getLog(JaTooTelegramBotWeatherCommand.class);

  private static final String APPID = "0b438dbe7fb5d24ce8ca273834fc988c";

  private final JaTooWeatherService weatherService;

  public JaTooTelegramBotWeatherCommand() {
    super("weather", "get the current weather");
    this.weatherService = new JaTooWeatherOpenWeatherMap(APPID);
  }

  @Override
  public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {

    JaTooWeather weather = weatherService.getWeather("683506");

    StringBuilder text = new StringBuilder();
    text.append("<strong>").append(weather.getCity()).append("</strong>").append(" (").append(weather.getDescriptionText()).append(")");
    text.append("\n").append(weather.getTemperatureTextFull());
    text.append("\n").append(weather.getWindTextFull());
    text.append("\n").append(weather.getCloudsTextFull());
    text.append("\n").append(weather.getHumidityTextFull());
    // text.append("\n").append(weatherService.getPressureWithText(weather));
    // text.append("\n").append(weatherService.getWindDirectionWithText(weather));
    // text.append("\n").append(weatherService.getRainWithText(weather));
    // text.append("\n").append(weatherService.getSnowWithText(weather));
    // text.append("\n").append(weatherService.getSunriseWithText(weather));
    // text.append("\n").append(weatherService.getSunsetWithText(weather));

    SendMessage response = new SendMessage();
    response.setChatId(chat.getId().toString());
    response.enableHtml(true);
    response.setText(text.toString());

    try {
      absSender.sendMessage(response);
    } catch (TelegramApiException e) {
      LOGGER.error("failed to send the response to the /" + getCommandIdentifier() + " command", e);
    }
  }

}