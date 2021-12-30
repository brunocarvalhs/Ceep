package br.brunocarvalhs.ceep.model

import java.io.Serializable

class Note(title: String?, describe: String?) : Serializable {

    var title: String? = title
        get() = field

    var describe: String? = describe

}