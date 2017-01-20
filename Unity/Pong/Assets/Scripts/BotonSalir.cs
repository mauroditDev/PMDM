using UnityEngine;
using UnityEngine.SceneManagement;
using System.Collections;

public class BotonSalir : MonoBehaviour {
    public pulsarPantalla unP;
    public pulsarPantalla dosP;

	void Start()
    {
        Debug.Log(SceneManager.GetActiveScene().name);
        Debug.Log(SceneManager.GetSceneByName("start").buildIndex);
    }
	// Update is called once per frame
	void Update () {

        if (Input.GetKey(KeyCode.Escape))
        {
            if (SceneManager.GetActiveScene().name.Equals("start"))
            {
                Application.Quit();
            }
            else
            {
                SceneManager.LoadScene(SceneManager.GetSceneByName("start").buildIndex);
            }
        }
        if (SceneManager.GetActiveScene().name.Equals("start") && unP.pulsado)
        {
            SceneManager.LoadScene(1);
            this.gameObject.GetComponent<Vidas>().resetVidas();
        }
        if (SceneManager.GetActiveScene().name.Equals("start") && dosP.pulsado)
        {
            SceneManager.LoadScene(2);
            this.gameObject.GetComponent<Vidas>().resetVidas();
        }
    }
}
