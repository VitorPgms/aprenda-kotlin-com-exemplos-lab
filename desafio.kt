enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

class Usuario(val nome: String, var nivel: Nivel)

data class ConteudoEducacional(var nome: String, val duracao: Int = 60)

data class Formacao(val nome: String, var nivel: Nivel, var conteudos: List<ConteudoEducacional>) {

    val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario) {
        if(usuario !in inscritos){
            inscritos.add(usuario)
            println("${usuario.nome} foi cadastrado com sucesso")
        } else {
            println("Usuario já cadastrado")
        }
    }

    fun listarInscritos() {
        println("Inscritos em $nome")
        if (inscritos.isEmpty()) {
            println("Nenhum aluno foi inscrito")
        } else {
            inscritos.forEachIndexed { index, usuario ->
                println("${index + 1}. ${usuario.nome} ${usuario.nivel}")
            }
        }
    }
}

fun main() {
    val kotlin = ConteudoEducacional("Kotlin",20)
    val css = ConteudoEducacional("Css",10)

    val formacaoKotlinBackEnd = Formacao("Formação Kotlin BackEnd",  Nivel.BASICO, listOf(kotlin))
    val formacaoKotlinAndroid = Formacao("Formação Kotlin Android",  Nivel.BASICO, listOf(kotlin))
    val formacaoKotlinWeb = Formacao("Formação Kotlin Web",  Nivel.BASICO, listOf(kotlin))

    do {
        println("Digite seu nome")
        val nome = readln()!!

        println("Qual o seu nivel de Conhecimento:")
        println("1- Basico")
        println("2- Intermediario")
        println("3- Avançado")
        val conhecimento = when(readln()!!.toInt()) {
            1 -> Nivel.BASICO
            2 -> Nivel.INTERMEDIARIO
            3 -> Nivel.AVANCADO
            else -> Nivel.BASICO
        }

        println("Qual Formação voce deseja")
        println("1- Formação Kotlin BackEnd")
        println("2- Formação Kotlin Android")
        println("3- Formação Kotlin Web")
        val selecFormacao = when(readln()!!.toInt()) {
            1 -> formacaoKotlinBackEnd
            2 -> formacaoKotlinAndroid
            3 -> formacaoKotlinWeb
            else -> formacaoKotlinAndroid
        }

        val usuario = Usuario(nome, conhecimento)
        selecFormacao.matricular(usuario)

        println("Deseja Cadastrar outro Usuario? (s/n)")
        val resposta = readln()!!.lowercase()
    } while (resposta == "s")

    formacaoKotlinBackEnd.listarInscritos()
    formacaoKotlinAndroid.listarInscritos()
    formacaoKotlinWeb.listarInscritos()


}
