Feature: Obtener detalles de un elemento

  Scenario: Obtener detalles de un elemento desconocido
    Given que realizo una solicitud para obtener detalles de un elemento desconocido
    When la solicitud se procesa correctamente
    Then se recibe una respuesta exitosa con detalles del elemento
    And se muestran los detalles del elemento como nombre, año, color y valor Pantone


Feature: Actualizar detalles de un usuario

  Scenario: Actualizar los detalles de un usuario existente
    Given que deseo actualizar los detalles de un usuario
    When envío una solicitud de actualización con un nuevo nombre y trabajo
    Then la solicitud se procesa correctamente
    And se confirma que el trabajo del usuario ha sido actualizado


Feature: Registro de usuario

  Scenario Outline: Registrar un nuevo usuario con diferentes credenciales

    Given que deseo registrarme con un nuevo correo electrónico <email> y contraseña <password>
    When envío una solicitud de registro con el correo electrónico y la contraseña
    Then se procesa correctamente la solicitud de registro
    And se recibe una respuesta exitosa confirmando el registro del nuevo usuario

    Examples:
      | email                | password  |
      | German               | 123       |
      | Alex@gmail.com        | Alex123 |
      | Diego@gmail.com       | Diego123    |
