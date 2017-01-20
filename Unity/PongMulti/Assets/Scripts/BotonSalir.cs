using UnityEngine;
using UnityEngine.SceneManagement;
using System.Collections;

public class BotonSalir : MonoBehaviour {
    
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
        if (SceneManager.GetActiveScene().name.Equals("start") && Input.GetButtonDown("Fire1"))
        {
            SceneManager.LoadScene(SceneManager.GetActiveScene().buildIndex+1);
            this.gameObject.GetComponent<Vidas>().Reset();
        }

    }
}
